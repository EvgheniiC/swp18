package userManagement;

import Token.ISessionToken;
import com.google.common.eventbus.EventBus;
import databaseService.IDataAccessObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import Server.Server;
import swp18e.messages.toServer.*;

import java.util.ArrayList;
import java.util.List;

public class UserManagementTest  {
    private UserManagement uM;
    private String testUser;
    private String testPassword;
    private int testDbNr;
    private IUserDB userDB;
    private IDataAccessObject dataAccessObject;
    private UserC userTest;


    @Before
    public void setUp(){

        testUser = "testUserJUnit";
        testPassword = "testUserJUnit";
        testDbNr = 0;
        userTest = new UserC(testUser, testDbNr, 0);


        Server.sessionToken = mock(ISessionToken.class);
        Server.events = mock(EventBus.class);
        userDB = mock(IUserDB.class);
        dataAccessObject = mock(IDataAccessObject.class);


        uM = new UserManagement(userDB, dataAccessObject);

        //UserManagment Mock
        when(uM.getUser(testUser)).thenReturn(userTest);

        //UserDB Mock-Objekt
        when(userDB.addUser(new UserC(testUser, testDbNr,0))).thenReturn(true);
        when(userDB.userExists("abcd")).thenReturn(false);
        when(userDB.findUser(testUser)).thenReturn(userTest);



        //Datenbank Mock-Objekt
        when(dataAccessObject.getDbNr(testUser)).thenReturn(testDbNr);
        when(dataAccessObject.getPassword(testDbNr)).thenReturn(testPassword);
        when(dataAccessObject.getPassword(testUser)).thenReturn(testPassword);
        when(dataAccessObject.nameIsAvailable(testUser)).thenReturn(false);
        when(dataAccessObject.setPassword(testUser, testPassword+"1")).thenReturn(true);
        when(dataAccessObject.setUsername(testUser, testUser+"!")).thenReturn(true);

        // SessionToken Mock Objekt
        when(Server.sessionToken.getChannel(0)).thenReturn(null);


    }


    @Test
    public void comparePasswordTest() {
        assertEquals(uM.comparePassword(testUser, testPassword), true);
        assertEquals(uM.comparePassword(testUser,testPassword+"1"), false);

    }

    @Test
    public void loginTrue() {
        LoginRequest message = new LoginRequest(testUser,testPassword ,0 );
        when(userDB.userExists(testUser)).thenReturn(false);

        assertEquals(uM.login(message), true);

    }
    @Test
    public void loginFalse() {
        LoginRequest message = new LoginRequest(testUser, testPassword + "1", 0);
        when(userDB.userExists(testUser)).thenReturn(false);

        assertEquals(uM.login(message), false);

    }

    @Test
    public void loginFalseUserLoggedIn() {
        LoginRequest message = new LoginRequest(testUser, testPassword + "1", 0);
        when(userDB.userExists(testUser)).thenReturn(true);

        assertEquals(uM.login(message), false);

    }

    @Test
    public void loginFalseUserDontExist() {
        LoginRequest message = new LoginRequest(testUser, testPassword + "1", 0);
        when(dataAccessObject.nameIsAvailable(testUser)).thenReturn(true);

        assertEquals(uM.login(message), false);

    }


    @Test
    public void logoutTrue() {
        LogoutRequest message = new LogoutRequest(testUser, 0);
        when(userDB.userExists(testUser)).thenReturn(true);

        assertEquals(uM.logout(message), true);
    }

    @Test
    public void logoutFalse() {
        LogoutRequest message = new LogoutRequest(testUser, 0);
        when(userDB.userExists(testUser)).thenReturn(false);

        assertEquals(uM.logout(message), false);
    }

    @Test
    public void forceLogoutTrue() {
        CloseConnectionClientRequest message = new CloseConnectionClientRequest(testUser, 0);
        when(userDB.userExists(testUser)).thenReturn(true);

        assertEquals(uM.forceLogout(message), true);
    }

    @Test
    public void forceLogoutFalse() {
        CloseConnectionClientRequest message = new CloseConnectionClientRequest(testUser, 0);
        when(userDB.userExists(testUser)).thenReturn(false);

        assertEquals(uM.forceLogout(message), false);
    }

    @Test
    public void registerUserTrue() {
        RegisterRequest message = new RegisterRequest(testUser, testPassword, 0);
        when(dataAccessObject.nameIsAvailable(testUser)).thenReturn(true);

        assertEquals(uM.registerUser(message), true);

    }

    @Test
    public void registerUserFalse() {
        RegisterRequest message = new RegisterRequest(testUser, testPassword, 0);
        when(dataAccessObject.nameIsAvailable(testUser)).thenReturn(false);

        assertEquals(uM.registerUser(message), false);

    }

    @Test
    public void changePasswordTrue() {
        EditPasswordRequest message = new EditPasswordRequest(testUser, testPassword, testPassword+"1", 0);

        assertEquals(uM.changePassword(message), true);

    }

    @Test
    public void changePasswordFalse() {
        EditPasswordRequest message = new EditPasswordRequest(testUser, testPassword+"!", testPassword+"1", 0);

        assertEquals(uM.changePassword(message), false);

    }

    @Test
    public void changeUsernameTrue() {
        EditUsernameRequest message = new EditUsernameRequest(testUser, testUser+"!", testPassword, 0);
        when(dataAccessObject.nameIsAvailable(testUser+"!")).thenReturn(true);

        assertEquals(uM.changeUsername(message), true);
    }

    @Test
    public void changeUsernameFalse() {
        EditUsernameRequest message = new EditUsernameRequest(testUser, testUser+"!", testPassword, 0);
        when(dataAccessObject.nameIsAvailable(testUser+"!")).thenReturn(false);

        assertEquals(uM.changeUsername(message), false);
    }

    @Test
    public void changeUsernamePasswordFalse() {
        EditUsernameRequest message = new EditUsernameRequest(testUser, testUser+"!", testPassword+"1", 0);
        when(dataAccessObject.nameIsAvailable(testUser+"!")).thenReturn(false);

        assertEquals(uM.changeUsername(message), false);
    }

    @Test
    public void getUser() {
    }

    @Test
    public void getAllClients() {
    }

    @Test
    public void getActiveClients() {
        List<UserC> allClients= new ArrayList<>();
        List<UserC> result= new ArrayList<>();
        allClients.add(userTest);
        result.add(userTest);
        UserC test = new UserC("test", 1, 0);
        test.changeStatus();
        allClients.add(test);
        when(uM.getAllClients()).thenReturn(allClients);

        assertEquals(uM.getActiveClients(), result);

    }

    @Test
    public void getInactiveClients() {
        List<UserC> allClients= new ArrayList<>();
        List<UserC> result= new ArrayList<>();
        allClients.add(userTest);
        UserC test = new UserC("test", 1, 0);
        test.changeStatus();
        allClients.add(test);
        result.add(test);
        when(uM.getAllClients()).thenReturn(allClients);

        assertEquals(uM.getInactiveClients(), result);

    }

    @Test
    public void clientsToUsernames() {
        List<UserC> allClients= new ArrayList<>();
        UserC test = new UserC("test", 1, 0);
        allClients.add(userTest);
        allClients.add(test);
        List<String> result = new ArrayList();
        result.add(testUser);
        result.add("test");

        assertEquals(uM.clientsToUsernames(allClients), result);

    }

    @Test
    public void encryptPassword() {
    }
}