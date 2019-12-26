package swp18e.messages.toServer.game;

import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.RequestMessageObject;

public class GameStartRequest extends RequestMessageObject {

    private GameIdentifier gameIdentifier;

    public GameStartRequest(String username, Integer token, GameIdentifier gameIdentifier) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
