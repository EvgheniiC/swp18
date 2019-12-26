package swp18e.messages.toClient;

import swp18e.messages.GameIdentifier;

public class JoinLobbyResponse extends ResponseMessageObject {

    private int lobbyToken;
    private GameIdentifier gameIdentifier;

    public JoinLobbyResponse(String username, boolean result, String reason, Integer token, GameIdentifier gameIdentifier) {
        super(username, result, reason, token);
        this.gameIdentifier = gameIdentifier;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
