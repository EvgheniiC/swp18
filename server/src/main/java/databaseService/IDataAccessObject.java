package databaseService;

import java.sql.SQLException;

public interface IDataAccessObject {
    String host = "duemmer.informatik.uni-oldenburg.de:48050";
    String password = "scfyrc1";
    String database = "UserDB";
    String dbUser = "root";

    String getUsername(int dbIds);

    int getDbNr(String username);

    String getPassword(int dbId);

    String getPassword(String username);

    boolean setPassword(int id, String password);

    boolean setPassword(String username, String password);

    boolean createUser(String username, String password);

    String exceptionReturn(SQLException e);

    boolean nameIsAvailable(String name);

    boolean setUsername(String oldUsername, String newUsername);


}
