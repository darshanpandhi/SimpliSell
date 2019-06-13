package com.simplisell.tests.business;

import com.simplisell.business.AccessUsers;
import com.simplisell.business.Credentials;
import com.simplisell.objects.User;

import static org.junit.Assert.*;
import org.junit.Test;

public class CredentialsTest {
    @Test
    public void testCorrectPassword()
    {
        System.out.println("\nStarting testCorrectPassword: correct password for user");

        Credentials credentials = new Credentials();
        AccessUsers userList = new AccessUsers();

        User user = new User("User1", "123456", "What is your favourite color", "Red");
        userList.insertNewUser(user);
        String passwordInput = "123456";
        assertNotNull(credentials.authenticate("User1", passwordInput));

        System.out.println("Finished testCorrectPassword: correct password for user");
    }

    @Test
    public void testWrongPassword()
    {
        System.out.println("\nStarting testCorrectPassword: incorrect password for user");

        Credentials credentials = new Credentials();
        AccessUsers userList = new AccessUsers();

        User user = new User("User1", "123456", "What is your favourite color", "Red");
        userList.insertNewUser(user);
        String userNameInput = "User1";
        String passwordInput = "1234567";
        assertNull(credentials.authenticate(userNameInput, passwordInput));

        System.out.println("Finished testCorrectPassword: incorrect password for user");
    }

    @Test
    public void testCorrectSecurityAnswer()
    {
        System.out.println("\nStarting testCorrectSecurityAnswer: correct answer for user security question");

        Credentials credentials = new Credentials();
        AccessUsers userList = new AccessUsers();

        User user = new User("User1", "123456", "What is your favourite color", "Red");
        userList.insertNewUser(user);
        String userNameInput = "User1";
        String securityQuestionAnswer = "Red";
        assertTrue(credentials.correctSecurityAnswer(userNameInput, securityQuestionAnswer));

        System.out.println("Finished testCorrectSecurityAnswer: correct answer for user security question");
    }

    @Test
    public void testIncorrectSecurityAnswer()
    {
        System.out.println("\nStarting testIncorrectSecurityAnswer: incorrect answer for user security question");

        Credentials credentials = new Credentials();
        AccessUsers userList = new AccessUsers();

        User user = new User("User1", "123456", "What is your favourite color", "Red");
        userList.insertNewUser(user);
        String userNameInput = "User1";
        String securityQuestionAnswer = "Green";
        assertFalse(credentials.correctSecurityAnswer(userNameInput, securityQuestionAnswer));

        System.out.println("Finished testIncorrectSecurityAnswer: incorrect answer for user security question");
    }
    @Test
    public void userNameNotFound()
    {
        System.out.println("\nStarting testCorrectPassword: username not found");

        Credentials credentials = new Credentials();
        AccessUsers userList = new AccessUsers();

        User user = new User("User1", "123456", "What is your favourite color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "InvalidUser";
        String passwordInput = "123456";
        assertNull(credentials.authenticate(userNameInput, passwordInput));

        System.out.println("Finished testCorrectPassword: username not found");
    }

    @Test
    public void testValidPasswordLength()
    {
        System.out.println("\nStarting testValidPassword: password has length greater than or equal to 6 and less than or equal to 12");

        Credentials credentials = new Credentials();

        String password = "123456";
        boolean isValid = credentials.validPassword(password);
        assertTrue(isValid);
        password = "qwertyuiopas";
        isValid = credentials.validPassword(password);
        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has length greater than or equal to 6 and less than or equal to 12");
    }

    @Test
    public void testValidPasswordLowerCaseLetters()
    {
        System.out.println("\nStarting testValidPassword: password has lowercase letters");

        Credentials credentials = new Credentials();

        String password = "abcdefghijkl";
        boolean isValid = credentials.validPassword(password);
        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has lowercase letters");
    }

    @Test
    public void testValidPasswordUpperCaseLetters()
    {
        System.out.println("\nStarting testValidPassword: password has uppercase letters");

        Credentials credentials = new Credentials();

        String password = "ABCDEFGH";
        boolean isValid = credentials.validPassword(password);
        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has uppsercase letters");
    }

    @Test
    public void testValidPasswordNumbers()
    {
        System.out.println("\nStarting testValidPassword: password has numbers");

        Credentials credentials = new Credentials();

        String password = "123456789";
        boolean isValid = credentials.validPassword(password);
        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has number");
    }

    @Test
    public void testValidPasswordLettersAndNumMix()
    {
        System.out.println("\nStarting testValidPassword: password has letters and numbers");
        Credentials credentials = new Credentials();

        String password = "aBc1d34";
        boolean isValid = credentials.validPassword(password);
        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has letters and numbers");
    }

    @Test
    public void testInvalidPasswordLength()
    {
        System.out.println("\nStarting testValidPassword: password has length less than 6 or greater than to 12");

        Credentials credentials = new Credentials();

        String password = "12345";
        boolean isValid = credentials.validPassword(password);
        assertFalse(isValid);
        password = "abcdefghijklm";
        isValid = credentials.validPassword(password);
        assertFalse(isValid);

        System.out.println("Finished testValidPassword: password has length less than 6 or greater than to 12");
    }

    @Test
    public void testInvalidPasswordCharacters()
    {
        System.out.println("\nStarting testValidPassword: password has an invalid character (not a letter or number)");

        Credentials credentials = new Credentials();

        String password = "!_&abcd";
        boolean isValid = credentials.validPassword(password);
        assertFalse(isValid);
        password = "aaaaaa  ";
        isValid = credentials.validPassword(password);
        assertFalse(isValid);

        System.out.println("Finished testValidPassword: password has an invalid character (not a letter or number)");
    }
}
