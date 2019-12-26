package swp18e.messages.toClient.updateMessage;

import color.Color;
import swp18e.messages.GameIdentifier;

import java.util.List;

public class UpdateWagonCards extends UpdateMessage {

    private List<Color> wagonCards;
    private GameIdentifier gameIdentifier;
    private String username;
    private int token;
    public UpdateWagonCards(String username, int token, GameIdentifier gameIdentifier,
                            List<Color> wagonCards) {

        this.token = token;
        this.username = username;
        this.gameIdentifier = gameIdentifier;
        this.wagonCards = wagonCards;
    }

    public GameIdentifier getGameIdentifier(){
        return gameIdentifier;
    }

    public String getUsername() {
        return username;
    }

    public int getToken() {
        return token;
    }

    public List<Color> getWagonCards() {
        return wagonCards;
    }
}