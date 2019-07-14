package com.simplisell.tests.business;

import com.simplisell.business.AccessUsers;
import com.simplisell.business.Credentials;
import com.simplisell.objects.User;
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

        User User = new User("UserFullName","User1",
                "123456", "What is your favourite color", "Red",
                null, null, false);
        accessUsers.insertNewUser(User);
        String passwordInput = "123456";

        assertNotNull(credentials.correctPassword("User1", passwordInput));

        System.out.println("Finished testCorrectPassword: correct password for user");
    }

    @Test
    public void testWronagPassword()
    {
        System.out.println("\nStarting testCorrectPassword: incorrect password for user");

        User User = new User("User1Name","User1",
                "123456", "What is your favourite color", "Red",
                null, null, false);
        accessUsers.insertNewUser(User);
        String userNameInput = "User1";
        String passwordInput = "1234567";

        assertNull(credentials.correctPassword(userNameInput, passwordInput));

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
//        assertNull(credentials.correctPassword(userNameInput, passwordInput));
//
//        System.out.println("Finished testCorrectPassword: username not found");
//    }
}
