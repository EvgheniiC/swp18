package swp18e.messages.toClient.userManagement;

import swp18e.messages.toClient.ResponseMessageObject;

import java.util.List;

public class LoginSuccessfulResponse extends ResponseMessageObject {

    private List<String> activeUser;
    private List<String> inActiveUser;
    private List<String> allUser;

    public LoginSuccessfulResponse(String username, List<String> activeUser, List<String> inActiveUser, List<String> allUser, Integer token){
        super(username,true,"login successful", token);
        this.activeUser = activeUser;
        this.inActiveUser = inActiveUser;
        this.allUser = allUser;
    }


    public List<String> getActiveUser() {
        return activeUser;
    }

    public List<String> getAllUser() {
        return allUser;
    }

    public List<String> getInActiveUser() {
        return inActiveUser;
    }

    public void setActiveUser(List<String> activeUser) {
        this.activeUser = activeUser;
    }

    public void setAllUser(List<String> allUser) {
        this.allUser = allUser;
    }

    public void setInActiveUser(List<String> inActiveUser) {
        this.inActiveUser = inActiveUser;
    }
}
