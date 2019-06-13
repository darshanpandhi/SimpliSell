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
}
