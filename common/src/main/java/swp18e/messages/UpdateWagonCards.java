package swp18e.messages;

import java.util.List;

public class UpdateWagonCards extends GameMessage {
    private List<String> wagonCards;

    public UpdateWagonCards(String playerUsername, int playerToken, GameIdentifier gameIdentifier,
                            List<String> wagonCards) {

        super(playerUsername, playerToken, gameIdentifier);
        this.wagonCards = wagonCards;
    }


    public List<String> getWagonCards() {
        return wagonCards;
    }
}