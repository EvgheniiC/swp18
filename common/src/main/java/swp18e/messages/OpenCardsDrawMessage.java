package swp18e.messages;

import java.util.List;

public class OpenCardsDrawMessage extends MessageObject {

    private GameIdentifier gameIdentifier;

    //man kann 1 Zugkarte der offenen Zugkarten durch die oberste Karte der verdeckten Stapel ersetzen
    private List<String> currentOpenCards;

    public OpenCardsDrawMessage(String username, Integer token, GameIdentifier gameIdentifier, List<String> currentOpenCards) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
        this.currentOpenCards = currentOpenCards;
    }


    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }

    public List<String> getCurrentOpenCards() {
        return currentOpenCards;
    }

}