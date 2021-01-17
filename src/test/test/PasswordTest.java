package test;

import model.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Test Password Class
public class PasswordTest {

    private Password password1;
    private Password password2;

    @BeforeEach
    public void setUp() {
        password1 = new Password("999999");
        password2 = new Password("898989");
    }

    @Test
    public void testgetPasswordAndDecryption() {
        assertTrue((password1.getPassword()).equals("999999"));
        assertTrue((password2.getPassword()).equals("898989"));
        assertFalse((password1.getPassword()).equals("898989"));
        assertFalse((password2.getPassword()).equals("999999"));
    }

    @Test
    public void testresetPasswordAndEncryption() {
        password1.resetPassword("111111");
        password2.resetPassword("222222");
        assertTrue((password1.getPassword()).equals("111111"));
        assertTrue((password2.getPassword()).equals("222222"));
        assertFalse((password1.getPassword()).equals("222222"));
        assertFalse((password2.getPassword()).equals("111111"));
    }

    @Test
    public void testConstructorNotEncryptPW()  {
        Password password3 = new Password("13579;", true);
        assertTrue((password3.getPassword()).equals("123456"));
    }

    @Test
    public void testgetPasswordEncrypted() {
        password1.resetPassword("123456");
        assertTrue((password1.getPasswordEncrypted()).equals("13579;"));
    }
}
