package com.simplisell.tests.business;

import com.simplisell.business.AccessUsers;
import com.simplisell.business.Credentials;
import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;
import com.simplisell.persistence.UserPersistence;
import com.simplisell.tests.persistence.UserPersistenceStub;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CredentialsTest
{
    private Credentials credentials;
    private AccessUsers accessUsers;

    @Before
    public void setup()
    {
        UserPersistenceStub userStub = new UserPersistenceStub();
        credentials = new Credentials(userStub);
        accessUsers = new AccessUsers(userStub);
    }

    @Test
    public void testCorrectPassword()
    {
        System.out.println("\nStarting testCorrectPassword: correct password for user");

        UserAdvertiser userAdvertiser = new UserAdvertiser("UserFullName","User1",
                "123456", "What is your favourite color", "Red",
                null, null, null, 0);
        accessUsers.insertNewUserAdvertiser(userAdvertiser);
        String passwordInput = "123456";

        assertNotNull(credentials.authenticate("User1", passwordInput));

        System.out.println("Finished testCorrectPassword: correct password for user");
    }

    @Test
    public void testWrongPassword()
    {
        System.out.println("\nStarting testCorrectPassword: incorrect password for user");

        UserAdvertiser userAdvertiser = new UserAdvertiser("User1Name","User1",
                "123456", "What is your favourite color", "Red",
                null, null, null, 0);
        accessUsers.insertNewUserAdvertiser(userAdvertiser);
        String userNameInput = "User1";
        String passwordInput = "1234567";

        assertNull(credentials.authenticate(userNameInput, passwordInput));

        System.out.println("Finished testCorrectPassword: incorrect password for user");
    }

//    @Test
//    public void testCorrectSecurityAnswer()
//    {
//        System.out.println("\nStarting testCorrectSecurityAnswer: correct answer for user security question");
//
//        User user = new User("a1","User1", "123456", "What is your favourite color", "Red", null, null, null);
//        accessUsers.insertNewUser(user);
//        String userNameInput = "User1";
//        String securityQuestionAnswer = "Red";
//
//        assertTrue(credentials.correctSecurityAnswer(userNameInput, securityQuestionAnswer));
//
//        System.out.println("Finished testCorrectSecurityAnswer: correct answer for user security question");
//    }
//
//    @Test
//    public void testIncorrectSecurityAnswer()
//    {
//        System.out.println("\nStarting testIncorrectSecurityAnswer: incorrect answer for user security question");
//
//        User user = new User("asd","User1", "123456", "What is your favourite color", "Red", null, null, null);
//        accessUsers.insertNewUser(user);
//        String userNameInput = "User1";
//        String securityQuestionAnswer = "Green";
//
//        assertFalse(credentials.correctSecurityAnswer(userNameInput, securityQuestionAnswer));
//
//        System.out.println("Finished testIncorrectSecurityAnswer: incorrect answer for user security question");
//    }
//    @Test
//    public void userNameNotFound()
//    {
//        System.out.println("\nStarting testCorrectPassword: username not found");
//
//        User user = new User("da da","User1", "123456", "What is your favourite color", "Red", null, null, null);
//        User addedUser = accessUsers.insertNewUser(user);
//        String userNameInput = "InvalidUser";
//        String passwordInput = "123456";
//
//        assertNull(credentials.authenticate(userNameInput, passwordInput));
//
//        System.out.println("Finished testCorrectPassword: username not found");
//    }

    @Test
    public void testValidPasswordLength()
    {
        System.out.println("\nStarting testValidPassword: password has length greater than or equal to 6 and less than or equal to 12");

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

        String password = "abcdefghijkl";
        boolean isValid = credentials.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has lowercase letters");
    }

    @Test
    public void testValidPasswordUpperCaseLetters()
    {
        System.out.println("\nStarting testValidPassword: password has uppercase letters");

        String password = "ABCDEFGH";
        boolean isValid = credentials.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has uppsercase letters");
    }

    @Test
    public void testValidPasswordNumbers()
    {
        System.out.println("\nStarting testValidPassword: password has numbers");

        String password = "123456789";
        boolean isValid = credentials.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has number");
    }

    @Test
    public void testValidPasswordLettersAndNumMix()
    {
        System.out.println("\nStarting testValidPassword: password has letters and numbers");

        String password = "aBc1d34";
        boolean isValid = credentials.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has letters and numbers");
    }

    @Test
    public void testInvalidPasswordLength()
    {
        System.out.println("\nStarting testValidPassword: password has length less than 6 or greater than to 12");

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

        String password = "!_&abcd";
        boolean isValid = credentials.validPassword(password);
        assertFalse(isValid);
        password = "aaaaaa  ";
        isValid = credentials.validPassword(password);

        assertFalse(isValid);

        System.out.println("Finished testValidPassword: password has an invalid character (not a letter or number)");
    }
}
