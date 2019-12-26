package user;

import java.util.ArrayList;
import java.util.List;

public class AlreadyConnectedUsers {
    private List<String> connectedUsers = new ArrayList<>();
    public static AlreadyConnectedUsers alreadyConnectedUsers = new AlreadyConnectedUsers();

    public List<String> getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(List<String> connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public boolean addConnectedUser(String username){
       return connectedUsers.add(username);
    }

    public boolean removeUser(String username){
        return connectedUsers.remove(username);
    }

    public boolean changeUsername(String oldUsername, String newUsername){
        if(removeUser(oldUsername)){
            return addConnectedUser(newUsername);
        }
        return false;
    }
}
