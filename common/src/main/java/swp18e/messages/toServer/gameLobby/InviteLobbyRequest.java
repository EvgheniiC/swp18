package swp18e.messages.toServer.gameLobby;

import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.RequestMessageObject;

public class InviteLobbyRequest extends RequestMessageObject {
    private String targetUser;
    private GameIdentifier gameIdentifier;

    public InviteLobbyRequest(String username, Integer token, String targetUser, GameIdentifier gameIdentifier) {
        super(username, token);
        this.targetUser = targetUser;
        this.gameIdentifier = gameIdentifier;
    }

    public String getTargetUser() {
        return targetUser;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
