package lobby;

import swp18e.messages.GameIdentifier;
import userManagement.UserC;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private int lobbyToken;
    private List<UserC> lobbyUser = new ArrayList<>();
    private String lobbyName;
    private int memberCounter;
    private GameIdentifier gameIdentifier;

    public Lobby(int lobbyToken, String lobbyName) {
        this.lobbyToken = lobbyToken;
        this.lobbyName = lobbyName;
        memberCounter = 0;
        gameIdentifier = new GameIdentifier(lobbyToken, lobbyName);
    }

    public int getLobbyToken() {
        return lobbyToken;
    }

    public boolean addUser(UserC user) {
        if(memberCounter<5) {
            lobbyUser.add(user);
            memberCounter++;
            return true;
        }else return false;
    }

    public List<Integer> getAllUserTokens() {
        List<Integer> userTokenList = new ArrayList<>();
        for (UserC u : lobbyUser) {
            userTokenList.add(u.getToken());
        }
        return userTokenList;
    }

    public List<String> getAllUsernames() {
        List<String> UserList = new ArrayList<>();
        for (UserC u : lobbyUser) {
            UserList.add(u.getUsername());
        }
        return UserList;
    }

    public boolean removeUser(UserC user) {
        memberCounter--;
        return lobbyUser.remove(user);

    }

    public List<UserC> getLobbyUser() {
        return lobbyUser;
    }

    public UserC getUserByToken(int token){
        for(UserC userC: lobbyUser){
            if(userC.getToken() == token){
                return userC;
            }
        }
        return null;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public int getMemberCounter() {
        return memberCounter;
    }

    public GameIdentifier getGameIdentifier() {
        return gameIdentifier;
    }
}
