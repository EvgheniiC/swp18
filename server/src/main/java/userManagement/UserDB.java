package userManagement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDB implements IUserDB{
    private List<UserC> userList;

    public UserDB(){
        userList = new LinkedList<>();
    }
    @Override
    public boolean removeUser(UserC user){
        if(!user.getStatus()){
            userList.remove(user);
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public boolean addUser(UserC user){
        userList.add(user);
        return true;
    }

    @Override
    public List<UserC> getAllClients() {
        return userList;
    }

    @Override
    public List<String> getUsersAsStrings() {
        List<String> users = new ArrayList<>();
        for (UserC user : getAllClients()) {
            users.add(user.getUsername());
        }
        return users;
    }

    @Override
    public UserC findUser(String username) {
        for (UserC u: userList){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    public boolean userExists(String username){
        for (UserC u: userList){
            if(u.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
}
