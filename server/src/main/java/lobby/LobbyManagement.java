package lobby;

import Token.ILobbyToken;
import Token.LobbyToken;
import Server.Server;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import game.Game;
import swp18e.messages.*;
import swp18e.messages.toClient.gameLobby.CreateLobbyResponse;
import swp18e.messages.toClient.gameLobby.InvitationResponse;
import swp18e.messages.toClient.gameLobby.JoinLobbyResponse;
import swp18e.messages.toClient.updateMessage.GameStartResponse;
import swp18e.messages.toClient.updateMessage.UpdateGameMessage;
import swp18e.messages.toServer.*;
import swp18e.messages.toServer.game.GameMessage;
import swp18e.messages.toServer.game.GameStartRequest;
import swp18e.messages.toServer.gameLobby.AnswerInviteRequest;
import swp18e.messages.toServer.gameLobby.CreateLobbyRequest;
import swp18e.messages.toServer.gameLobby.DeleteLobbyRequest;
import swp18e.messages.toServer.gameLobby.InviteLobbyRequest;
import userManagement.IUserManagement;
import userManagement.UserC;
import userManagement.UserManagement;

import java.util.logging.Logger;

import java.util.List;

public class LobbyManagement {

    private ILobbyToken lobbyToken;
    private IMap lobbyMap;
    private IUserManagement userManagement;
    private static Logger log = Logger.getLogger(LobbyManagement.class.getName());

    public LobbyManagement(IUserManagement userManagement, IMap lobbyMap, ILobbyToken lobbyToken) {
        this.userManagement = userManagement;
        log.setLevel(Server.logLevel);
        this.lobbyMap = lobbyMap;
        this.lobbyToken = lobbyToken;
    }


    @Subscribe
    public Lobby createLobby(CreateLobbyRequest message) {
        int token = lobbyToken.createToken();
        log.info("Lobby erstellen. Token: "+ token+ "Lobbyname: "+ message.getLobbyName());
        lobbyMap.setLobby(token, new Lobby(token, message.getLobbyName()));
        lobbyMap.getLobby(token).addUser(userManagement.getUser(message.getUsername()));

        CreateLobbyResponse clr = new CreateLobbyResponse(message.getUsername(), true, "Lobby erstellt", message.getToken(), new GameIdentifier(token, message.getLobbyName()));
        Server.sessionToken.getChannel(message.getToken()).writeAndFlush(clr);
        Server.events.post(new UpdateGameMessage(new GameIdentifier(token,message.getLobbyName())));
        return lobbyMap.getLobby(token);
    }

    @Subscribe
    public boolean addUserToLobby(AnswerInviteRequest message) {
        Lobby lobby = lobbyMap.getLobby(message.getGameIdentifier().getId());
        if (message.getAnswer()) {
            boolean result = lobby.addUser(userManagement.getUser(message.getUsername()));
            if(result) {
                InvitationResponse ir = new InvitationResponse(message.getUsername(), true, "Einladung angenommen", userManagement.getUser(message.getUsername()).getToken());

                Server.sessionToken.getChannel(userManagement.getUser(message.getOriginUser()).getToken()).writeAndFlush(ir);
                Server.events.post(new JoinLobbyResponse(message.getUsername(), true, "Lobby beigetretten", message.getToken(), message.getGameIdentifier()));
                Server.events.post(new LobbyUserUpdateMessage(message.getGameIdentifier(), lobby.getAllUserTokens(), lobby.getAllUsernames()));
            }else {
                Server.events.post(new JoinLobbyResponse(message.getUsername(), false, "Lobby ist bereits voll", message.getToken(), message.getGameIdentifier()));
            }
        } else {
            Server.events.post(new InvitationResponse(message.getUsername(), false, message.getUsername()+" hat abgelehnt", userManagement.getUser(message.getOriginUser()).getToken()));
            return false;
        }
        return false;
    }

    @Subscribe
    public List<UserC> removeUserFromLobby(LeaveLobbyResult message) {
        Lobby lobby = lobbyMap.getLobby(message.getGameIdentifier().getId());
        lobby.removeUser(userManagement.getUser(message.getUsername()));
        Server.events.post(new LobbyUserUpdateMessage(message.getGameIdentifier(),lobby.getAllUserTokens(), lobby.getAllUsernames()));
        if (lobby.getAllUserTokens().isEmpty()) {
            lobbyMap.removeLobby(lobby.getLobbyToken());
            lobbyToken.removeToken(lobby.getLobbyToken());
            return null;
        }
        return lobby.getLobbyUser();
    }

    @Subscribe
    public void inviteUserToLobby(InviteLobbyRequest message) {
        int targetToken = userManagement.getUser(message.getTargetUser()).getToken();
        Server.events.post(new InvitedMessage(message.getGameIdentifier(), message.getUsername(), targetToken));
    }

    @Subscribe
    public Lobby deleteLobby(DeleteLobbyRequest message){
        Lobby lobby = lobbyMap.getLobby(message.getGameIdentifier().getId());
        Server.events.post(new LobbyDeletedMessage(lobby.getAllUserTokens(), message.getGameIdentifier()));
        lobbyMap.removeLobby(message.getGameIdentifier().getId());
        lobbyToken.removeToken(message.getGameIdentifier().getId());
        return lobbyMap.getLobby(message.getGameIdentifier().getId());
    }

    @Subscribe
    public Game startGame(GameStartRequest message){
        int token = message.getGameIdentifier().getId();
        Lobby lobby = lobbyMap.getLobby(token);
        if(lobby.getMemberCounter()>1) {
            lobbyMap.setGame(token, new Game(lobby));
            lobbyMap.setEventBus(token, new EventBus());
            lobbyMap.getEventBus(token).register(lobbyMap.getGame(token));

            Server.events.post(new GameStartedMessage(lobby.getAllUserTokens(), message.getGameIdentifier()));

        }else {
            Server.events.post(new GameStartResponse(message.getUsername(), false, "Nicht genug Spieler in der Lobby", message.getToken(), message.getGameIdentifier()));
        }
        return null;
    }

    @Subscribe
    public void postGameMessages(GameMessage gameMessage){
        lobbyMap.getEventBus(gameMessage.getGameIdentifier().getId()).post(gameMessage);
    }

    public Lobby getLobbyByToken(int token){
        return lobbyMap.getLobby(token);
    }

}
