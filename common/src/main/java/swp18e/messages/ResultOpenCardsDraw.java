package swp18e.messages;

public class ResultOpenCardsDraw extends ResultMessage {

    public ResultOpenCardsDraw(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
