package databaseService;

import java.sql.*;
import java.util.logging.Logger;

public class DataAccessObject implements IDataAccessObject {
    private static final String connectionpath = "jdbc:mysql://" + host + "/" + database + "?user=" + dbUser + "&password=" + password;
    private Connection con;
    Statement stmt;
    private String result;
    ResultSet resultSet;
    private int intResult;
    private final static Logger log = Logger.getLogger(DataAccessObject.class.getName());

    public DataAccessObject() {
        try {
            con = DriverManager.getConnection(connectionpath);
            stmt = con.createStatement();
        } catch (SQLException e) {
            exceptionReturn(e);
        }
    }


    @Override
    public String getUsername(int dbId) {
        try {
            resultSet = stmt.executeQuery("SELECT Username FROM User WHERE ID = " + dbId);
            while (resultSet.next()) {
                result = resultSet.getString("Username");
            }
        } catch (SQLException e) {
            exceptionReturn(e);
        }
        return result;

    }

    @Override
    public int getDbNr(String username) {
        try {
            resultSet = stmt.executeQuery("SELECT ID FROM User WHERE Username = '" + username + "'");
            while (resultSet.next()) {
                intResult = resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            exceptionReturn(e);
        }
        return intResult;

    }


    @Override
    public String getPassword(String username) {
        try {
            resultSet = stmt.executeQuery("SELECT Password FROM User WHERE Username = '" + username + "'");
            while (resultSet.next()) {
                result = resultSet.getString("Password");
            }

        } catch (SQLException e) {
            exceptionReturn(e);
        }
        return result;

    }
    @Override
    public String getPassword(int dbID){
        try {
            resultSet = stmt.executeQuery("SELECT Password FROM User WHERE ID = "+ dbID);
            while (resultSet.next()) {
                result = resultSet.getString("Password");
            }

        } catch (SQLException e) {
            exceptionReturn(e);
        }
        return result;
    }

    @Override
    public boolean setPassword(int dbId, String password) {
        try {
            result = "" + stmt.executeUpdate("UPDATE User SET Password='" + password + "' WHERE ID=" + dbId);
        } catch (SQLException e) {
            exceptionReturn(e);
            return false;
        }
        return true;


    }

    @Override
    public boolean setPassword(String username, String password) {
        try {
            result = "" + stmt.executeUpdate("UPDATE User SET Password='" + password + "' WHERE Username = '" + username + "'");
        } catch (SQLException e) {
            exceptionReturn(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean createUser(String username, String password) {
        if (nameIsAvailable(username)) {
            try {
                result = "" + stmt.executeUpdate("INSERT INTO User(Username, Password) VALUES('" + username + "','" + password + "')");
                return true;
            } catch (SQLException e) {
                exceptionReturn(e);
                return false;
            }
        } else {
            System.out.println("Benutzer Name ist schon vergeben");
            return false;
        }
    }

    @Override
    public boolean nameIsAvailable(String username) {
        boolean nameIsAvailable = true;
        try {
            this.resultSet = stmt.executeQuery("SELECT Username FROM User WHERE Username = '" + username + "'");
            while (this.resultSet.next()) {
                nameIsAvailable = false;
            }

        } catch (SQLException e) {
            exceptionReturn(e);
        }
        return nameIsAvailable;
    }
    @Override
    public boolean setUsername(String oldUsername, String newUsername){
        try {
            result = "" + stmt.executeUpdate("UPDATE User SET Username='" + newUsername + "' WHERE Username = '" + oldUsername + "'");
        } catch (SQLException e) {
            exceptionReturn(e);
            return false;
        }
        return true;
    }


    @Override
    public String exceptionReturn(SQLException e) {
        log.warning(e.toString());
        return e.toString();

    }

}