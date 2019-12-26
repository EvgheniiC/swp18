package swp18e.messages;

import swp18e.messages.toServer.RequestMessageObject;

public class LeaveLobbyResult extends RequestMessageObject {

    private GameIdentifier gameIdentifier;

    public LeaveLobbyResult(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
