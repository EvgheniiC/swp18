package swp18e.messages;

public class ResultWagonCardsDraw extends ResultMessage {
    public ResultWagonCardsDraw(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}

