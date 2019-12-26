package swp18e.messages.toClient;

import swp18e.messages.GameIdentifier;

public class CreateLobbyResponse extends ResponseMessageObject {

    private GameIdentifier gameIdentifier;

    public CreateLobbyResponse(String username, boolean result, String reason, Integer token, GameIdentifier gameIdentifier) {
        super(username, result, reason, token);
        this.gameIdentifier = gameIdentifier;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
