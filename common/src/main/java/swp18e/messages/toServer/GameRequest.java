package swp18e.messages.toServer;

import swp18e.messages.GameIdentifier;

public class GameRequest extends RequestMessageObject {

    private GameIdentifier gameIdentifier;

    public GameRequest(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
