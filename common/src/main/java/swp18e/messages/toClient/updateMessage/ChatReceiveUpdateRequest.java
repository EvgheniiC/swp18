package swp18e.messages.toClient.updateMessage;


import swp18e.messages.toClient.updateMessage.UpdateMessage;

public class ChatReceiveUpdateRequest extends UpdateMessage
{

    private String username;
    private String message;

    public ChatReceiveUpdateRequest(String username, String message)
    {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
