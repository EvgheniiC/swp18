package swp18e.messages;

import io.netty.channel.Channel;

import java.io.Serializable;

public class TokenMessage implements Serializable {
    private int token;

    public TokenMessage(Integer token) {
        this.token = token;
    }

    public int getToken(){
        return token;
    }
}
