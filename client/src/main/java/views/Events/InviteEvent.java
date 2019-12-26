package views.Events;

import swp18e.messages.GameIdentifier;

public class InviteEvent {
    private String originUser;
    private GameIdentifier gameIdentifier;

    public InviteEvent(String originUser, GameIdentifier gameIdentifier) {
        this.originUser = originUser;
        this.gameIdentifier = gameIdentifier;
    }

    public String getOriginUser() {
        return originUser;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
