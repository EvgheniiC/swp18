package swp18e.messages;

public class WagonCardsDrawMessage extends MessageObject {
    private GameIdentifier gameIdentifier;

    //"Wagenkarten nehmen" Aktion: Spieler kann 1 oder 2 Zugkarten vom verdeckten Stapel ziehen
    //"Tunnels" Aktion: Spieler muss 3 Zugkarten vom verdeckten Stapel ziehen ziehen
    private int numberDraw;
    private boolean isTunnelAction;

    public WagonCardsDrawMessage(String username, Integer token, GameIdentifier gameIdentifier,boolean isTunnelAction, int numberDraw) {
        super(username, token);
        this.gameIdentifier = gameIdentifier;
        this.isTunnelAction = isTunnelAction;
        this.numberDraw = numberDraw;
    }


    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }

    public int getNumberDraw() {
        return numberDraw;
    }

    public boolean getIsTunnelAction(){
        return isTunnelAction;
    }
}
