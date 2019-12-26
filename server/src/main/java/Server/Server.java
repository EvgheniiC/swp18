package Server;

import Token.ISessionToken;
import Token.LobbyToken;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import databaseService.DataAccessObject;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lobby.LobbyManagement;
import lobby.Map;
import swp18e.messages.*;
import Token.SessionToken;
import swp18e.messages.toClient.ResponseMessageObject;
import swp18e.messages.toClient.updateMessage.UpdateMessage;
import swp18e.messages.toServer.userManagement.CloseConnectionClientRequest;
import userManagement.UserDB;
import userManagement.UserManagement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static EventBus events;
    private static UserManagement userManagement;
    private static Server server;
    public static ISessionToken sessionToken;
    public static LobbyManagement lobbyManagement;
    private static Logger log = Logger.getLogger(Server.class.getName());
    public final static Level logLevel = Level.SEVERE;


    public static void main(String[] args) {
        events = new EventBus();
        sessionToken = new SessionToken();
        userManagement = new UserManagement(new UserDB(), new DataAccessObject());
        lobbyManagement = new LobbyManagement(userManagement, new Map(), new LobbyToken());

        events.register(lobbyManagement);
        events.register(userManagement);
        Chat.Chat.start();
        log.setLevel(logLevel);
        try {
            server = new Server();
            events.register(server);
            server.start();
        } catch (Exception e) {
            log.warning(e.toString());
        }
    }

    private void start() throws Exception {
        log.setLevel(Level.INFO);
        log.info("Server wurde gestart");
        log.setLevel(logLevel);

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            new ServerBootstrap()
                    .group(eventLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {

                        @Override
                        protected void initChannel(Channel channel) {

                            channel.pipeline()
                                    .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)))
                                    .addLast(new ObjectEncoder())
                                    .addLast(new ServerEventBusHandler());

                        }
                    }).bind(48055).sync().channel().closeFuture().syncUninterruptibly();

        } finally {
            eventLoopGroup.shutdownGracefully();
            log.info("Server beendet");
        }
    }

    @Subscribe
    public void sendMessage(ResponseMessageObject message) {
        log.info("Nachricht gesendet. Username: "+message.getUsername()+ "Token: "+message.getToken()+"NachrichtenTyp: "+message.toString());
        Channel target = sessionToken.getChannel(message.getToken());
        target.writeAndFlush(message);

        if (!message.getResult()){
            log.info((message.getReason()));
        }
    }


    @Subscribe
    public void sendAllMessage(UpdateMessage message) {
        log.info("Nachricht an alle gesendet. " +"NachrichtenTyp: "+message.toString());
        for (Channel channel:sessionToken.getAllChannel()) {
            channel.writeAndFlush(message);
        }
    }

    @Subscribe
    public void closeConnection(CloseConnectionClientRequest message) {
        try {
            log.info("Client hat die Verbindung beeendet. Token: "+message.getToken()+ "NachrichtenTyp: "+message.toString());
            sessionToken.getChannel(message.getToken()).close().sync();
            Server.sessionToken.deleteToken(message.getToken());
        } catch (InterruptedException e) {
            log.warning(e.toString());
        }
    }

    @Subscribe
    public void sendToken(TokenMessage message) {
        sessionToken.getChannel(message.getToken()).writeAndFlush(message);
    }

    @Subscribe
    public void sendLobbyMessage(LobbyMessage messsage){
        log.info("Nachricht an die Lobby gesendet. LobbyToken: "+messsage.getGameIdentifier().getId()+"NachrichtenTyp: "+messsage.toString());
        for(int i:messsage.getLobbyUserToken()){
            sessionToken.getChannel(i).writeAndFlush(messsage);
        }
    }

    @Subscribe
    public void sendInviteMessage(InvitedMessage message){
        log.info("Einladung gesendet. ZielTokem: "+message.getToken()+"NachrichtenTyp: "+ message.toString());
        sessionToken.getChannel(message.getToken()).writeAndFlush(message);
    }


}