package com.simplisell.tests.business;

import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;

import static org.junit.Assert.*;
import org.junit.Test;

public class AccessUsersTest {
    @Test
    public void testAddUniqueUserName()
    {
        System.out.println("\nStarting insertNewUser: unique username");

        AccessUsers userList = new AccessUsers();
        User user = new User("User1", "123456", "What is your favourite color", "Red");
        User addedUser = userList.insertNewUser(user);
        assertNotNull(addedUser);
        assertEquals("User1",addedUser.getUserName());
        assertEquals("123456", addedUser.getPassword());

        System.out.println("Finished insertNewUser: unique username");
    }

    @Test
    public void testAddDuplicateUserName()
    {
        System.out.println("\nStarting insertNewUser: username exist");

        AccessUsers userList = new AccessUsers();
        User user = new User("User1", "123456", "What is your favourite color", "Red");
        User addedUser = userList.insertNewUser(user);
        addedUser = userList.insertNewUser(user);
        assertNull(addedUser);

        System.out.println("Finished insertNewUser: username exist");
    }

    @Test
    public void testCorrectPassword()
    {
        System.out.println("\nStarting testCorrectPassword: correct password for user");

        AccessUsers userList = new AccessUsers();
        User user = new User("User1", "123456", "What is your favourite color", "Red");
        User addedUser = userList.insertNewUser(user);
        String passwordInput = "123456";
        assertTrue(userList.correctPassword("User1", passwordInput));

        System.out.println("Finished testCorrectPassword: correct password for user");
    }

    @Test
    public void testWrongPassword()
    {
        System.out.println("\nStarting testCorrectPassword: incorrect password for user");

        AccessUsers userList = new AccessUsers();
        User user = new User("User1", "123456", "What is your favourite color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "User1";
        String passwordInput = "1234567";
        assertFalse(userList.correctPassword(userNameInput, passwordInput));

        System.out.println("Finished testCorrectPassword: incorrect password for user");
    }

    @Test
    public void testCorrectSecurityAnswer()
    {
        System.out.println("\nStarting testCorrectSecurityAnswer: correct answer for user security question");

        AccessUsers userList = new AccessUsers();
        User user = new User("User1", "123456", "What is your favourite color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "User1";
        String securityQuestionAnswer = "Red";
        assertTrue(userList.correctSecurityAnswer(userNameInput, securityQuestionAnswer));

        System.out.println("Finished testCorrectSecurityAnswer: correct answer for user security question");
    }

    @Test
    public void testIncorrectSecurityAnswer()
    {
        System.out.println("\nStarting testIncorrectSecurityAnswer: incorrect answer for user security question");

        AccessUsers userList = new AccessUsers();
        User user = new User("User1", "123456", "What is your favourite color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "User1";
        String securityQuestionAnswer = "Green";
        assertFalse(userList.correctSecurityAnswer(userNameInput, securityQuestionAnswer));

        System.out.println("Finished testIncorrectSecurityAnswer: incorrect answer for user security question");
    }
    @Test
    public void userNameNotFound()
    {
        System.out.println("\nStarting testCorrectPassword: username not found");

        AccessUsers userList = new AccessUsers();
        User user = new User("User1", "123456", "What is your favourite color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "InvalidUser";
        String passwordInput = "123456";
        assertFalse(userList.correctPassword(userNameInput, passwordInput));

        System.out.println("Finished testCorrectPassword: username not found");
    }
}
