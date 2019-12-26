package databaseService;

import org.junit.Before;
import org.junit.Test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataAccessObjectTest {

    private String testUser;
    DataAccessObject dbAccess = new DataAccessObject();

    private SQLException sqlException = mock(SQLException.class);


    @Before
    public void setUp(){
        testUser = "testUserJUnit";
        dbAccess.resultSet = mock(ResultSet.class);

    }


    @Test
    public void getUsernameTrue() {
        assertEquals(dbAccess.getUsername(19), "testUserJUnit");
    }

    @Test
    public void getUsernameFalse() {
        assertEquals(dbAccess.getUsername(1), null);
    }

    @Test
    public void getDbNrTrue() {
        assertEquals(dbAccess.getDbNr(testUser), 19);
    }


    @Test
    public void getDbNrFalse() {
        assertEquals(dbAccess.getDbNr(testUser+"1"), 0);
    }

    @Test
    public void getPasswordNrTrue() {
        assertEquals(dbAccess.getPassword(19), testUser);
    }

    @Test
    public void getPasswordNrFalse() {
        assertEquals(dbAccess.getPassword(0), null);
    }
    @Test
    public void getPasswordUsernameTrue() {
        assertEquals(dbAccess.getPassword(testUser), testUser);
    }

    @Test
    public void getPasswordNrUsernameFalse() {
        assertEquals(dbAccess.getPassword(0), null);
    }

    @Test
    public void setPasswordNr() throws SQLException {
        dbAccess.stmt = mock(Statement.class);
        when(dbAccess.stmt.executeQuery("UPDATE User SET Password='" + "test" + "' WHERE ID=" + 19)).thenReturn(dbAccess.resultSet);
        assertEquals(dbAccess.setPassword(19, "test"), true);
        }

    @Test
    public void setPasswordUserName() throws SQLException {
        dbAccess.stmt = mock(Statement.class);
        when(dbAccess.stmt.executeQuery("UPDATE User SET Password='" + "test" + "' WHERE ID=" + 19)).thenReturn(dbAccess.resultSet);
        assertEquals(dbAccess.setPassword(testUser, testUser), true);
    }

    @Test
    public void createUserTrue() throws SQLException {
        dbAccess.stmt = mock(Statement.class);
        when(dbAccess.stmt.executeQuery("SELECT Username FROM User WHERE Username = '" + "ManuelABCD" + "'")).thenReturn(dbAccess.resultSet);
        assertEquals(dbAccess.createUser("ManuelABCD", "test"), true);
    }

    @Test
    public void createUserFalse(){
        assertEquals(dbAccess.createUser(testUser, "test"), false);
    }

    @Test
    public void nameIsAvailableTrue() {
        assertEquals(dbAccess.nameIsAvailable("ManuelABCD"), true);

    }

    @Test
    public void nameIsAvailableFalse() {
        assertEquals(dbAccess.nameIsAvailable(testUser), false);

    }

    @Test
    public void setUsername() throws SQLException {
        dbAccess.stmt = mock(Statement.class);
        when(dbAccess.stmt.executeUpdate("UPDATE User SET Username='" + testUser + "' WHERE Username = '" + testUser + "'")).thenReturn(0);
        assertEquals(dbAccess.setUsername(testUser, testUser), true);
    }

    @Test
    public void exceptionReturn() {
        assertEquals(dbAccess.exceptionReturn(sqlException), ""+sqlException);
    }
}