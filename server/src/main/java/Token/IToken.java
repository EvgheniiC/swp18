package Token;

import java.util.List;

public interface IToken {
    int createToken();

    void removeToken(int token);

    List<Integer> getAllTokens();
}
