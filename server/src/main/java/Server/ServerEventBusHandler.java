package Server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import swp18e.messages.TokenMessage;
import swp18e.messages.toServer.RequestMessageObject;

import java.util.logging.Logger;

public class ServerEventBusHandler implements ChannelInboundHandler {
    private static Logger log = Logger.getLogger(ServerEventBusHandler.class.getName());

    public ServerEventBusHandler(){
        log.setLevel(Server.logLevel);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) {

        int token = Server.sessionToken.addToken(channelHandlerContext.channel());
        Server.events.post(new TokenMessage(token));
        log.info("Client hat sich verbunden. Token: "+ token + " Channel: "+channelHandlerContext.channel().toString());

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) {


    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) {

    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) {

    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) {
        Server.events.post(o);
        if(o instanceof RequestMessageObject) {

            log.info("Nachricht empfangen. Username: "+((RequestMessageObject) o).getUsername()+" Token: "+((RequestMessageObject) o).getToken()+" Nachrichtentyp: "+o.toString());
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }
}
