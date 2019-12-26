package swp18e.messages.toServer;



import java.io.Serializable;

public class RequestMessageObject implements Serializable {
    private String username;
    private Integer token;

    public RequestMessageObject(String username, Integer token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }
}
