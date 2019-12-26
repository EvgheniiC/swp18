package swp18e.messages.toClient.gameLobby;

import swp18e.messages.toClient.ResponseMessageObject;

public class InvitationResponse extends ResponseMessageObject {
    public InvitationResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
