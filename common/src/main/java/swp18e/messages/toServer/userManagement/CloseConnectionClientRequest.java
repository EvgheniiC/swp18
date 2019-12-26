package swp18e.messages.toServer.userManagement;

import swp18e.messages.toServer.RequestMessageObject;

public class CloseConnectionClientRequest extends RequestMessageObject {
    private String message = "Verbindung ist getrennt.";


    public CloseConnectionClientRequest(String username, Integer token){
        super(username, token);
    }

    public CloseConnectionClientRequest(String username, Integer token, String message){
        super(username, token);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

}
