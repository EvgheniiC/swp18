package swp18e.messages.toClient.updateMessage;

import java.util.List;

public class UpdateUserMessage extends UpdateMessage {

    private List<String> users;

    public UpdateUserMessage(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
