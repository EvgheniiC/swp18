package swp18e.messages.toClient.game;

import swp18e.messages.toClient.ResponseMessageObject;

public class ObjectiveCardsDrawResponse extends ResponseMessageObject {

    public ObjectiveCardsDrawResponse(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
