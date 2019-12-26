package swp18e.messages.toServer.gameLobby;

import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.RequestMessageObject;

public class DeleteLobbyRequest extends RequestMessageObject {

    private GameIdentifier gameIdentifier;

    public DeleteLobbyRequest(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
