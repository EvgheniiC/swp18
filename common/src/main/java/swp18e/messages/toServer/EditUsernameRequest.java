package swp18e.messages.toServer;

public class EditUsernameRequest extends RequestMessageObject {
    private String password;
    private String oldUsername;
    private String newUsername;

    public EditUsernameRequest(String oldUsername, String newUsername, String password, Integer token) {
        super(oldUsername, token);
        this.oldUsername = oldUsername;
        this.newUsername = newUsername;
        this.password = password;
    }


    public String getNewUsername() {
        return newUsername;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }


    public void setOldUsername(String oldUsername) {
        super.setUsername(oldUsername);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }
}



