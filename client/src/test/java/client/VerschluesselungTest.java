package client;

import org.junit.Test;
import static org.junit.Assert.*;

public class VerschluesselungTest {
    @Test
    public void encryptPassword() {
        String password = "123456789";
        String encryptPwd = "25f9e794323b453885f5181f1b624d0b";
        assertEquals(Verschluesselung.encryptPassword(password), encryptPwd);
    }
}
