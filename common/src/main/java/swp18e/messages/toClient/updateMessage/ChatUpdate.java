package swp18e.messages.toClient.updateMessage;


public class ChatUpdate extends UpdateMessage {

    private String username;
    private String message;

    public ChatUpdate(String username, String message) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChatUpdate) {
            if (this.username == ((ChatUpdate) obj).getUsername()) {
                if (this.message == ((ChatUpdate) obj).getMessage()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
