package com.simplisell.tests.business;

import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

public class AccessUsersTest {
    @Test
    public void testAddUniqueUserName()
    {
        System.out.println("\nStarting insertNewUser: unique username");

        AccessUsers userList = new AccessUsers();
        User user = new UserAdvertiser("User1", "1234");
        User addedUser = userList.insertNewUser(user);
        assertNotNull(addedUser);
        assertEquals("User1",addedUser.getUserName());
        assertEquals("1234", addedUser.getPassword());

        System.out.println("Finished insertNewUser: unique username");
    }

    @Test
    public void testAddDuplicateUserName()
    {
        System.out.println("\nStarting insertNewUser: username exists");

        AccessUsers userList = new AccessUsers();
        User user = new UserAdvertiser("User1", "1234");
        User addedUser = userList.insertNewUser(user);
        addedUser = userList.insertNewUser(user);
        assertNull(addedUser);

        System.out.println("Finished insertNewUser: username exists");
    }

    @Test
    public void testCorrectPassword()
    {
        System.out.println("\nStarting testCorrectPassword: correct password for user");

        AccessUsers userList = new AccessUsers();
        User user = new UserAdvertiser("User1", "1234");
        User addedUser = userList.insertNewUser(user);
        String passwordInput = "1234";
        assertTrue(userList.correctPassword("User1", passwordInput));

        System.out.println("Finished testCorrectPassword: correct password for user");
    }

    @Test
    public void testWrongPassword()
    {
        System.out.println("\nStarting testCorrectPassword: incorrect password for user");

        AccessUsers userList = new AccessUsers();
        User user = new UserAdvertiser("User1", "1234");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "User1";
        String passwordInput = "12345";
        assertFalse(userList.correctPassword(userNameInput, passwordInput));

        System.out.println("Finished testCorrectPassword: incorrect password for user");
    }

    @Test
    public void userNameNotFound()
    {
        System.out.println("\nStarting testCorrectPassword: username not found");

        AccessUsers userList = new AccessUsers();
        User user = new UserAdvertiser("User1", "1234");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "InvalidUser";
        String passwordInput = "1234";
        assertFalse(userList.correctPassword(userNameInput, passwordInput));

        System.out.println("Finished testCorrectPassword: username not found");
    }
}
