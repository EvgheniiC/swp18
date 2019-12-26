package userManagement;

import io.netty.channel.Channel;

public class UserC implements IUser{

    private String username;
    private boolean isAktiv;
    private int userNr;
    int token;

    public UserC(String username,int userNr, int token){
        isAktiv = true;
        this.userNr = userNr;
        this.username = username;
        this.token = token;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username=username;
    }

    @Override
    public void changeStatus() {
        isAktiv = !isAktiv;
    }

    @Override
    public boolean getStatus() {
        return isAktiv;
    }

    @Override
    public int getUserNr() {
        return userNr;
    }

    @Override
    public int getToken(){
        return token;
    }

    @Override
    public boolean equals(UserC user){
        if(username == user.getUsername() && userNr == user.getUserNr() && token == user.token){
            return true;
        }
        else{
            return false;
        }
    }
}