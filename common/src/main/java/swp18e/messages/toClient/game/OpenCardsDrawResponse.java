package swp18e.messages.toClient.game;

import swp18e.messages.toClient.ResponseMessageObject;

public class OpenCardsDrawResponse extends ResponseMessageObject {

    public OpenCardsDrawResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
