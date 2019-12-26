package client;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import javafx.application.Application;
import javafx.stage.Stage;
import swp18e.messages.toServer.RequestMessageObject;
import swp18e.messages.TokenMessage;
import views.PopUpWindow;
import views.ViewStart;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientInst extends Application{

    private static final String SERVER_HOST = "localhost";

    public static EventBus events;
    private static Channel channel;
    private static  ClientInst client;
    public static String username = null;
    public static int token;
    public static Stage activeStage;
    private final static Logger log = Logger.getLogger(ClientInst.class.getName());
    public final static Level logLevel = Level.SEVERE;

    public static void main(String[] args) {
        log.setLevel(logLevel);

        events = new EventBus("Client");

        EventLoopGroup group = new NioEventLoopGroup();
        client = new ClientInst();
        events.register(client);
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline()
                                    .addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)))
                                    .addLast(new ObjectEncoder())
                                    .addLast(new ClientEventBusHandler());
                        }
                    });

            channel = bootstrap.connect(SERVER_HOST, 48055).sync().channel();


            launch(args);

        } catch (InterruptedException e) {
            e.printStackTrace();
            log.warning(e.toString());
        } finally {
            group.shutdownGracefully();
        }


    }

    public static void closeChannel() {

        try {
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.warning(e.toString());
        }
    }

    public void start(Stage primaryStage){
        ViewStart.startLogin();
    }


    @Subscribe
    public void sendMessage(RequestMessageObject message) {
        channel.writeAndFlush(message);
        log.info("Nachricht gesendet. "+ message.toString());
    }

    @Subscribe
    public void getToken(TokenMessage message){
        log.info("Token bekommen Token: "+ message.getToken());
        token = message.getToken();
    }

    @Subscribe
    public void dead(DeadEvent message){
        log.warning("DeadEvent ausgelöst. Nachricht: "+ message.toString());

    }
}