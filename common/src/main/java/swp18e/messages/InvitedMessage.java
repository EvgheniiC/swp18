package swp18e.messages;

import java.io.Serializable;

public class InvitedMessage implements Serializable {
    private String originUser;
    private int token;
    private GameIdentifier gameIdentifier;


    public InvitedMessage(GameIdentifier gameIdentifier, String originUser, int token){
        this.gameIdentifier = gameIdentifier;
        this.originUser = originUser;
        this.token = token;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }

    public int getToken() {
        return token;
    }

    public String getOriginUser() {
        return originUser;
    }
}
