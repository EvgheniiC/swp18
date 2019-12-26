package swp18e.messages.toClient;

import java.io.Serializable;

public class ResponseMessageObject implements Serializable {
    private boolean result;
    private String reason;
    private String username;
    private Integer token;


    public ResponseMessageObject(String username, boolean result, String reason, Integer token){
            this.username = username;
            this.result = result;
            this.reason = reason;
            this.token = token;
        }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setResult(boolean result){
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public Integer getToken() {
        return token;
    }
}
