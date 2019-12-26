package swp18e.messages.toServer;


public class ChatRequest extends RequestMessageObject {
    private String message;

    public ChatRequest(String username, String message, int token)
    {
        super(username, token);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}


