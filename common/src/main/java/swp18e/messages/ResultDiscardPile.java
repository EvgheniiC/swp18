package swp18e.messages;

public class ResultDiscardPile extends ResultMessage {

    public ResultDiscardPile(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}

