package swp18e.messages.toClient.updateMessage;

import color.Color;
import swp18e.messages.GameIdentifier;
import swp18e.messages.LobbyMessage;

import java.util.List;

public class UpdateOpenCards extends LobbyMessage {


    private List<Color> openCards;

    public UpdateOpenCards(List<Integer> lobbyUserToken, GameIdentifier gameIdentifier,
                           List<Color> openCards) {

        super(lobbyUserToken, gameIdentifier);
        this.openCards = openCards;
    }

    public List<Color> getOpenCards() {
        return openCards;
    }
}