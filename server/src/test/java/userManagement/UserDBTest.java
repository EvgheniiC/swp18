package userManagement;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDBTest {

    private UserDB userDB;
    private UserC testUserA = new UserC("testUserA", 1, 0);
    private UserC testUserB = new UserC("testUserB", 2, 0);

    private List<UserC> result = new ArrayList();

    @Before
    public void setUp(){
        userDB = new UserDB();

        testUserA.changeStatus();
        userDB.addUser(testUserA);
        userDB.addUser(testUserB);


    }


    @Test
    public void removeUserTrue() {
        assertEquals(userDB.removeUser(testUserA), true);

    }

    @Test
    public void removeUserFalse() {
        assertEquals(userDB.removeUser(testUserB), false);

    }

    @Test
    public void addUser() {
    }

    @Test
    public void getAllClients() {
        result.add(testUserA);
        result.add(testUserB);

        assertEquals(userDB.getAllClients(), result);
    }

    @Test
    public void findUserSuccessful() {
        assertEquals(userDB.findUser("testUserA"), testUserA);
    }

    @Test
    public void findUserNull() {
        assertEquals(userDB.findUser("testUserC"), null);
    }

    @Test
    public void userExistsTrue() {
        assertEquals(userDB.userExists("testUserA"), true);
    }

    @Test
    public void userExistsFalse() {
        assertEquals(userDB.userExists("testUserC"), false);
    }
}