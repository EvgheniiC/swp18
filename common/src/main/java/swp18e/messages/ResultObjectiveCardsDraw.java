package swp18e.messages;

public class ResultObjectiveCardsDraw extends ResultMessage {

    public ResultObjectiveCardsDraw(String username, boolean result, String reason, Integer token) {
        super(username, result, reason, token);
    }
}
