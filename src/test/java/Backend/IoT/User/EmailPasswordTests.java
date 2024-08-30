package Backend.IoT.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class EmailPasswordTests {

    // Assuming UserService is the class that contains the IsValidUserForSignUp method

    @Autowired
    private UserService userService;

    @Test
    public void testValidEmailAndPassword() {
        assertTrue(userService.IsValidUserForSignUp("test@example.com", "Password1"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(userService.IsValidUserForSignUp("invalid-email", "Password1"));
    }

    @Test
    public void testInvalidPasswordTooShort() {
        assertFalse(userService.IsValidUserForSignUp("test@example.com", "short1"));
    }

    @Test
    public void testInvalidPasswordNoNumbers() {
        assertFalse(userService.IsValidUserForSignUp("test@example.com", "Password"));
    }

    @Test
    public void testInvalidPasswordNoLetters() {
        assertFalse(userService.IsValidUserForSignUp("test@example.com", "12345678"));
    }

    @Test
    public void testNullEmail() {
        assertFalse(userService.IsValidUserForSignUp(null, "Password1"));
    }

    @Test
    public void testNullPassword() {
        assertFalse(userService.IsValidUserForSignUp("test@example.com", null));
    }
}