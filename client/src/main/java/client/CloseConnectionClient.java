package client;


import swp18e.messages.toServer.userManagement.CloseConnectionClientRequest;

public class CloseConnectionClient {


    public static void closeConnection( final String username){
        try{
            CloseConnectionClientRequest msg = new CloseConnectionClientRequest(username, ClientInst.token);
            ClientInst.events.post(msg);
            ClientInst.closeChannel();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}