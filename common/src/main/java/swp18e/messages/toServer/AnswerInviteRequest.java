package swp18e.messages.toServer;

import swp18e.messages.GameIdentifier;

public class AnswerInviteRequest extends RequestMessageObject {

    private String originUser;
    private boolean answer;
    private GameIdentifier gameIdentifier;

    public AnswerInviteRequest(String username, Integer token, String originUser, GameIdentifier gameIdentifier, boolean answer) {
        super(username, token);
        this.originUser = originUser;
        this.gameIdentifier = gameIdentifier;
        this.answer = answer;
    }

    public String getOriginUser() {
        return originUser;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }

    public boolean getAnswer(){
        return answer;
    }

}
