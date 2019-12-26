package userManagement;

import java.util.List;

public interface IUserDB {

    boolean removeUser(UserC user);

    boolean addUser(UserC user);

    List<UserC> getAllClients();

    List<String> getUsersAsStrings();

    UserC findUser(String username);

    boolean userExists(String username);
}
