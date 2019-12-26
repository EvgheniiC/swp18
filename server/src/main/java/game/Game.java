package game;

import Server.Server;
import color.Color;
import com.google.common.eventbus.Subscribe;
import lobby.CardsDistribution;
import lobby.Lobby;
import swp18e.messages.toClient.game.DiscardPileMessage;
import swp18e.messages.toClient.updateMessage.UpdateOpenCards;
import swp18e.messages.toServer.game.*;
import userManagement.UserC;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Game {

    private HashMap<UserC, Player> players = new HashMap<>();
    private Board board;
    private CardsDistribution cardsDistribution;
    private Lobby lobby;
    private boolean isFirstDistribution;
    private Color[] playerColorList = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.YELLOW};
    private int playerNumber;
    private int roundCounter;

    public Game(Lobby lobby){
        isFirstDistribution  = true;
        cardsDistribution = new CardsDistribution();
        this.lobby = lobby;
        playerNumber=0;
        roundCounter=0;

        for(UserC user :lobby.getLobbyUser()){
            Player player = new Player();
            player.setUser(user);
            player.setPlayerColor(playerColorList[playerNumber]);
            player.setPlayerNumber(playerNumber);
            if(playerNumber==0){
                player.setTurn(true);
            }
            players.put(user, player);
            playerNumber++;
        }
    }

    @Subscribe
    public void firstGameRound(CardsFirstDistributionRequest message){
        if(isFirstDistribution) {
            isFirstDistribution = false;

            for (UserC user : lobby.getLobbyUser()) {
                Player player;
                player = players.get(user);
                cardsDistribution.firstDistribution(player, message.getGameIdentifier());
            }

            //------ 5 offen ausliegenden Karten
            List<Color> openCards = new ArrayList<>();
            openCards = cardsDistribution.drawOpenCards(openCards);
            Server.events.post(new UpdateOpenCards(lobby.getAllUserTokens(), message.getGameIdentifier(), openCards));
        }
    }

    @Subscribe
    public void objectiveCardsDrawAction(ObjectiveCardsDrawRequest message){
        Player player = players.get(lobby.getUserByToken(message.getToken()));
        if(player.isTurn()) {
            cardsDistribution.objectiveCardsDrawAction(player, message.getGameIdentifier());
        }
    }

    @Subscribe
    public void wagonCardsDrawAction(WagonCardsDrawRequest message){
        Player player = players.get(lobby.getUserByToken(message.getToken()));
        if(player.isTurn()) {
            cardsDistribution.wagonCardsDrawAction(players.get(lobby.getUserByToken(message.getToken())),
                    message.getIsTunnelAction(), message.getNumberDraw(), message.getGameIdentifier());
        }
    }

    @Subscribe
    public void openCardsDrawAction(OpenCardsDrawRequest message) {
        Player player = players.get(lobby.getUserByToken(message.getToken()));
        if (player.isTurn()) {

        cardsDistribution.openCardsDrawAction(lobby, player, message.getGameIdentifier(), message.getCurrentOpenCards());
        }
    }

    @Subscribe
    public void discardPileAction(DiscardPileMessage message){
        Player player = players.get(lobby.getUserByToken(message.getToken()));
        if(player.isTurn()) {
            cardsDistribution.discardPileAction(player, message.getWagonCards());
        }
    }

    @Subscribe
    public void endRound(EndRoundRequest message){
        Player player = players.get(lobby.getUserByToken(message.getToken()));
        nextRound(player);
    }

    public Lobby getLobby() {
        return lobby;
    }

    public HashMap<UserC, Player> allPlayers(){
        return players;
    }

    public Player getPlayer(int playerNumber){
        final Player[] player = new Player[1];
        players.forEach((v,k)->{
            if(k.getPlayerNumber() == playerNumber) {
                player[0] = k;
            }

        });
        return player[0];
    }
    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void nextRound(Player player){
        player.setTurn(false);
        if(roundCounter <=3){
            roundCounter++;
        }
        else roundCounter=0;
        players.forEach((v,k)->{
            if(k.getPlayerNumber() == roundCounter){
                k.setTurn(true);
            }
        });
    }
}