package Token;

import io.netty.channel.Channel;

import java.util.List;

public interface ISessionToken {
    int addToken(Channel channel);

    void deleteToken(int token);

    Channel getChannel(int token);

    List<Channel> getAllChannel();
}
