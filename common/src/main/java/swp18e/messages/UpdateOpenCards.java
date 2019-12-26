package swp18e.messages;

import java.util.List;

public class UpdateOpenCards extends LobbyMessage {


    private List<String> openCards;

    public UpdateOpenCards(List<Integer> lobbyUserToken, GameIdentifier gameIdentifier,
                           List<String> openCards) {

        super(lobbyUserToken, gameIdentifier);
        this.openCards = openCards;
    }

    public List<String> getOpenCards() {
        return openCards;
    }
}