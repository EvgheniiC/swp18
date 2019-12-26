package Token;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SessionToken extends Token implements ISessionToken {

    HashMap<Integer,Channel> map = new HashMap();

    @Override
    public int addToken(Channel channel){
        int token = createToken();
        map.put(token, channel);
        return token;
    }

    @Override
    public void deleteToken(int token){
        removeToken(token);
        map.remove(token);
    }

    @Override
    public Channel getChannel(int token){
        return map.get(token);
    }

    @Override
    public List<Channel> getAllChannel(){
        List<Channel> channel = new ArrayList<>();
        for(int token:tokens){
            channel.add(map.get(token));
        }
        return channel;
    }
}
