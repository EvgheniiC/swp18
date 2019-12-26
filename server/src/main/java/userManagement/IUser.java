package userManagement;

import io.netty.channel.Channel;

public interface IUser {

    String getUsername();

    void setUsername(String username);

    void changeStatus();

    boolean getStatus();

    int getUserNr();

    int getToken();

    boolean equals(UserC user);

}
