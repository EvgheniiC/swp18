package swp18e.messages.toServer.game;

import color.Color;
import swp18e.messages.GameIdentifier;
import swp18e.messages.toServer.game.GameMessage;

import java.util.List;

public class OpenCardsDrawRequest extends GameMessage {


    //man kann 1 Zugkarte der offenen Zugkarten durch die oberste Karte der verdeckten Stapel ersetzen
    private List<Color> currentOpenCards;

    public OpenCardsDrawRequest(String username, Integer token, GameIdentifier gameIdentifier, List<Color> currentOpenCards) {
        super(username, token, gameIdentifier);
        this.currentOpenCards = currentOpenCards;
    }


    public List<Color> getCurrentOpenCards() {
        return currentOpenCards;
    }

}