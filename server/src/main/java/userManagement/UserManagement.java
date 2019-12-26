package userManagement;

import Server.Server;
import com.google.common.eventbus.Subscribe;
import databaseService.IDataAccessObject;
import swp18e.messages.toClient.updateMessage.UpdateUserMessage;
import swp18e.messages.toClient.userManagement.*;
import swp18e.messages.toServer.userManagement.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManagement implements IUserManagement {



    IUserDB userDB;
    IDataAccessObject dbAccess;

    public UserManagement(IUserDB userDB, IDataAccessObject dbAccess){
        this.userDB = userDB;
        this.dbAccess = dbAccess;
    }

    @Override
    public boolean comparePassword(String username, String password) {
        password = encryptPassword(password);
        String userToComparePassword = dbAccess.getPassword(username);
        return userToComparePassword.equals(password);
    }

    @Override
    @Subscribe
    public boolean login(LoginRequest message) {
        String password = message.getPassword();
        String username = message.getUsername();
        password = encryptPassword(password);
        int token = message.getToken();
        if (!userDB.userExists(username)) {
            if(!dbAccess.nameIsAvailable(username)){
                if (this.comparePassword(username, password)) {
                    int userNr = dbAccess.getDbNr(username);
                    UserC user = new UserC(username, userNr, token);
                    userDB.addUser(user);
                    Server.events.post(new LoginSuccessfulResponse(username, clientsToUsernames(getActiveClients()), clientsToUsernames(getInactiveClients()), clientsToUsernames(getAllClients()), token));
                    Server.events.post(new UpdateUserMessage(userDB.getUsersAsStrings()));
                    return true;
                }else{
                    Server.events.post(new LoginResponse(username,false, "Passwort ist falsch", token));
                    return false;
                }
            }else{
                Server.events.post(new LoginResponse(username,false , "Benutzername ist nicht bekannt", token));
                return false;
            }
        } else {
            Server.events.post(new LoginResponse(username,false,"User ist bereits eingeloggt", token));
            return false;
        }
    }

    @Override
    @Subscribe
    public boolean logout(LogoutRequest message) {
        String username = message.getUsername();
        int token = message.getToken();

        if(userDB.userExists(username)){
            UserC user = this.getUser(username);
            if(user.getStatus()){
                user.changeStatus();
            }
            userDB.removeUser(user);
            Server.events.post(new UpdateUserMessage(userDB.getUsersAsStrings()));
            Server.events.post(new LogoutResponse(username, true,"Erfolgreich ausgeloggt", token));
            return true;

        }else{
            Server.events.post(new LogoutResponse(username, false,"User ist nicht eingeloggt", token));
            return false;
        }
    }

    @Override
    @Subscribe
    public boolean forceLogout(CloseConnectionClientRequest message) {
        String username = message.getUsername();
        if (userDB.userExists(username)) {
            UserC user = getUser(username);
            if (user.getStatus()) {
                user.changeStatus();
            }
            userDB.removeUser(user);
            Server.events.post(new UpdateUserMessage(userDB.getUsersAsStrings()));
            return true;
        }
        return false;
    }

    @Override
    @Subscribe
    public boolean registerUser(RegisterRequest message) {
        String password = message.getPassword();
        String username = message.getUsername();
        password =encryptPassword(password);
        int token = message.getToken();
        if(dbAccess.nameIsAvailable(username)) {
            dbAccess.createUser(username, password);
            Server.events.post(new RegisterResponse(username, true,"Erfolgreich registiert", token ));
            return true;
        }else{
            Server.events.post(new RegisterResponse(username, false, "Benutzername ist bereits vergeben", token));
            return false;
        }
    }

    @Override
    @Subscribe
    public boolean changePassword(EditPasswordRequest message){
        String username = message.getUsername();
        String oldPassword = message.getOldPassword();
        String newPassword = message.getNewPassword();
        int token = message.getToken();
        oldPassword = this.encryptPassword(oldPassword);
        newPassword = this.encryptPassword(newPassword);
        if(comparePassword(username, oldPassword)){
            dbAccess.setPassword(username,newPassword);
            Server.events.post(new EditResponse(username, true, "Password erfolgreich geändert", token));
            return true;
        }else{
            Server.events.post(new EditResponse(username, false, "Das alte Passwort ist nicht korrekt", token));
            return false;
        }
    }

    @Override
    @Subscribe
    public boolean changeUsername(EditUsernameRequest message){
        String password =encryptPassword(message.getPassword());
        String oldUsername = message.getOldUsername();
        String newUsername = message.getNewUsername();
        int token = message.getToken();
        if(comparePassword(oldUsername, password)){
            if(dbAccess.nameIsAvailable(newUsername)){
                dbAccess.setUsername(oldUsername,newUsername);
                userDB.findUser(oldUsername).setUsername(newUsername);
                Server.events.post(new EditResponse(newUsername, true, "Benutzername erfolgreich geändert", token) );
                Server.events.post(new UpdateUserMessage(userDB.getUsersAsStrings()));
                return true;
            }else{
                Server.events.post(new EditResponse(oldUsername, false, "Benutzer ist bereits vergeben", token));
                return false;
            }

        }else{
            Server.events.post(new EditResponse(oldUsername, false, "Passwort ist falsch", token));
            return false;
        }
    }

    @Override
    public UserC getUser(String username){
        return userDB.findUser(username);
    }

    @Override
    public List<UserC> getAllClients(){
        return userDB.getAllClients();
    }

    @Override
    public List<UserC> getActiveClients(){
        List<UserC> activeClients = new LinkedList<>();
        for (UserC u : this.getAllClients()){
            if (u.getStatus()){
                activeClients.add(u);
            }
        }
        return activeClients;
    }

    @Override
    public List<UserC> getInactiveClients(){
        List<UserC> inactiveClients = new LinkedList<>();
        for (UserC u : this.getAllClients()){
            if (!u.getStatus()){
                inactiveClients.add(u);
            }
        }
        return inactiveClients;
    }

    @Override
    public List<String> clientsToUsernames(List<UserC> clientList){
        List<String> clientUsernames = new ArrayList<>();
        for(UserC user: clientList){
            clientUsernames.add(user.getUsername());
        }
        return clientUsernames;
    }

    @Override
    public String encryptPassword(String password){                             //TODO    die Art der Verschlüsselung muss noch entschieden werden
        return password;
    }
}


