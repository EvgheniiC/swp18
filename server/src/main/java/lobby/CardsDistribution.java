package lobby;

import Server.Server;
import color.Color;
import game.Player;
import game.cards.DiscardPile;
import game.cards.objectiveCard.ObjectiveStackLongRoad;
import game.cards.objectiveCard.ObjectiveStackShortRoad;
import game.cards.wagonCard.WagonStack;
import cards.ObjectiveCard;
import swp18e.messages.GameIdentifier;
import swp18e.messages.toClient.game.DiscardPileResponse;
import swp18e.messages.toClient.game.ObjectiveCardsDrawResponse;
import swp18e.messages.toClient.game.OpenCardsDrawResponse;
import swp18e.messages.toClient.game.WagonCardsDrawResponse;
import swp18e.messages.toClient.updateMessage.UpdateObjectiveCards;
import swp18e.messages.toClient.updateMessage.UpdateOpenCards;
import swp18e.messages.toClient.updateMessage.UpdateWagonCards;
import java.util.LinkedList;
import java.util.List;

public class CardsDistribution implements ICardsDistribution{

    private ObjectiveStackShortRoad stackShortRoad;
    private ObjectiveStackLongRoad stackLongRoad;
    private WagonStack wagonStack;
    private DiscardPile discardPile;

    public CardsDistribution(){
        stackShortRoad = new ObjectiveStackShortRoad();
        stackLongRoad = new ObjectiveStackLongRoad();
        wagonStack = new WagonStack();
        discardPile = new DiscardPile();

    }

    @Override
    public void firstDistribution(Player player, GameIdentifier gameIdentifier){

        //------ 4 Zielkarten: 1 lange Strecke + 3 normale Strecken
        List<ObjectiveCard> objectiveCards = new LinkedList<>();
        objectiveCards.add(stackLongRoad.drawCard());
        objectiveCards.addAll(drawShortRoad());
        player.addObjectiveCards(objectiveCards);
        Server.events.post(new UpdateObjectiveCards(player.getUser().getUsername(), player.getUser().getToken(), gameIdentifier,
                objectiveCards));

        //------ 4 Zugkarten
        List<Color> wagonCardsColors = drawWagonCards(4);
        player.addWagonCards(wagonCardsColors);
        Server.events.post(new UpdateWagonCards(player.getUser().getUsername(), player.getUser().getToken(), gameIdentifier,
                wagonCardsColors));
    }
    @Override
    public void objectiveCardsDrawAction(Player player, GameIdentifier gameIdentifier){
        if(isDrawableStackShortRoad()){
            List<ObjectiveCard> shortRoadCards = drawShortRoad();
            player.addObjectiveCards(shortRoadCards);
            Server.events.post(new ObjectiveCardsDrawResponse(player.getUser().getUsername(), true,
                    "Zielkarten gezogen", player.getUser().getToken()));
            Server.events.post(new UpdateObjectiveCards(player.getUser().getUsername(), player.getUser().getToken(), gameIdentifier,
                    shortRoadCards));
        } else {
            Server.events.post(new ObjectiveCardsDrawResponse(player.getUser().getUsername(), false,
                    "keine Zielkarten mehr", player.getUser().getToken()));
        }
    }

    @Override
    public boolean wagonCardsDrawAction(Player player, boolean isTunnelAction, int numberDraw, GameIdentifier gameIdentifier){
        if((isTunnelAction && numberDraw != 3) || (!isTunnelAction && numberDraw > 2)){
            Server.events.post(new WagonCardsDrawResponse(player.getUser().getUsername(), false,
                    "ungültige Ziehungsnumber", player.getUser().getToken()));
            return false;
        }
        if(isDrawableWagonStack(numberDraw)){
            List<Color> wagonCardsColors = drawWagonCards(numberDraw);
            player.addWagonCards(wagonCardsColors);
            Server.events.post(new WagonCardsDrawResponse(player.getUser().getUsername(), true,
                    "Zugkarten gezogen", player.getUser().getToken()));
            Server.events.post(new UpdateWagonCards(player.getUser().getUsername(), player.getUser().getToken(), gameIdentifier,
                    wagonCardsColors));
            return true;
        }
        Server.events.post(new WagonCardsDrawResponse(player.getUser().getUsername(), false,
                "Ziehung nicht mehr möglich", player.getUser().getToken()));
        return false;
    }

    @Override
    public void openCardsDrawAction(Lobby lobby, Player player, GameIdentifier gameIdentifier, List<Color> currentOpenCards){

        if(isDrawableWagonStack(5-currentOpenCards.size())){
            Server.events.post(new OpenCardsDrawResponse(player.getUser().getUsername(), true,
                    "offene Karten gezogen", player.getUser().getToken()));
            Server.events.post(new UpdateOpenCards(lobby.getAllUserTokens(), gameIdentifier,
                    drawOpenCards(currentOpenCards)));
        } else {
            Server.events.post(new OpenCardsDrawResponse(player.getUser().getUsername(), false,
                    "Ziehung nicht mehr möglich", player.getUser().getToken()));
        }
    }

    @Override
    public void discardPileAction(Player player, List<Color> wagonCards){
        if(wagonCards != null){
            discardCardAll(wagonCards);
            player.removeWagonCards(wagonCards);
            Server.events.post(new DiscardPileResponse(player.getUser().getUsername(), false,
                    "Karten auf den Ablagestapel gelegt", player.getUser().getToken()));
        } else{
            Server.events.post(new DiscardPileResponse(player.getUser().getUsername(), false,
                    "Liste ist leer", player.getUser().getToken()));
        }

    }

    //-------------------------

    @Override
    public boolean isDrawableStackShortRoad(){
        return stackShortRoad.getSize() > 0;
    }

    @Override
    public boolean isDrawableWagonStack(int k){
        return wagonStack.getSize() >= k;
    }

    @Override
    public List<ObjectiveCard> drawShortRoad(){

        int drawNumber = 3;

        // Wenn der Stapel nur noch aus weniger als drei Zielkarten besteht,
        // kann der Spieler nur die vorhandene Anzahl Zielkarten ziehen
        if(stackShortRoad.getSize() < drawNumber){
            drawNumber = stackShortRoad.getSize();
        }

        List<ObjectiveCard> objectiveCards = new LinkedList<>();
        for(int i = 0; i<drawNumber; i++){
            objectiveCards.add(stackShortRoad.drawCard());
        }
        return objectiveCards;
    }

    @Override
    public List<Color> drawWagonCards(int numberDraw){

        List<Color> wagonCards = new LinkedList<>();
        for(int i = 0; i<numberDraw; i++){
            wagonCards.add(wagonStack.drawCard().getColor());
        }
        return wagonCards;
    }

    @Override
    public int getNumberJoker(List<Color> wagonCardsColors){
        int number = 0;
        if(wagonCardsColors != null){
            for(Color color: wagonCardsColors){
                if(color.equals(Color.JOKER)){
                    number = number + 1;
                }
            }
        }
        return number;
    }

    @Override
    public void discardCardAll(List<Color> wagonCardsColors){
        if(wagonCardsColors != null){
            for(Color color: wagonCardsColors){
                discardPile.discardCard(color);
            }
        }
    }

    @Override
    public List<Color> drawOpenCards(List<Color> openCardsColors){
        openCardsColors.addAll(drawWagonCards(5-openCardsColors.size()));
        while (getNumberJoker(openCardsColors) >= 3){
            discardCardAll(openCardsColors);
            openCardsColors = drawWagonCards(5);
        }
        return openCardsColors;
    }
}
