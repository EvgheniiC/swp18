package swp18e.messages.toServer.userManagement;

import swp18e.messages.toServer.RequestMessageObject;

public class EditPasswordRequest extends RequestMessageObject {

    private String oldPassword;
    private String newPassword;

    public EditPasswordRequest(String username, String oldPassword, String newPassword, Integer token){
        super(username, token);
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }


    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

}
