package swp18e.messages.toClient.updateMessage;

import swp18e.messages.GameIdentifier;
import swp18e.messages.toClient.ResponseMessageObject;

public class GameStartResponse extends ResponseMessageObject {
    private GameIdentifier gameIdentifier;

    public GameStartResponse(String username, boolean result, String reason, Integer token, GameIdentifier gameIdentifier) {
        super(username, result, reason, token);
        this.gameIdentifier = gameIdentifier;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
