package userManagement;

import swp18e.messages.toServer.userManagement.*;

import java.util.List;

public interface IUserManagement {

    boolean comparePassword(String username, String password);

    boolean login(LoginRequest message);

    boolean logout(LogoutRequest message);

    boolean registerUser(RegisterRequest message);

    UserC getUser(String username);

    List<UserC> getAllClients();

    boolean changePassword(EditPasswordRequest message);

    boolean changeUsername(EditUsernameRequest message);

    List<UserC> getActiveClients();

    List<UserC> getInactiveClients();

    List<String> clientsToUsernames(List<UserC> clientList);

    String encryptPassword(String password);

    boolean forceLogout(CloseConnectionClientRequest message);

}
