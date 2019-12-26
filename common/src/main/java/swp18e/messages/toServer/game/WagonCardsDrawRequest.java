package swp18e.messages.toServer.game;

import swp18e.messages.GameIdentifier;

public class WagonCardsDrawRequest extends GameMessage {

    //"Wagenkarten nehmen" Aktion: Spieler kann 1 oder 2 Zugkarten vom verdeckten Stapel ziehen
    //"Tunnels" Aktion: Spieler muss 3 Zugkarten vom verdeckten Stapel ziehen ziehen
    private int numberDraw;
    private boolean isTunnelAction;

    public WagonCardsDrawRequest(String username, Integer token, GameIdentifier gameIdentifier, boolean isTunnelAction, int numberDraw) {
        super(username, token, gameIdentifier);
        this.isTunnelAction = isTunnelAction;
        this.numberDraw = numberDraw;
    }

    public int getNumberDraw() {
        return numberDraw;
    }

    public boolean getIsTunnelAction(){
        return isTunnelAction;
    }
}
