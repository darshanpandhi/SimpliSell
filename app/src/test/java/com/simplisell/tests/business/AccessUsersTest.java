package com.simplisell.tests.business;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;


public class AccessUsersTest
{
    private AccessUsers userList;

    @Before
    public final void setup()
    {
        userList = new AccessUsers();
    }

    @Test
    public void testInsertUniqueUserName()
    {
        System.out.println("\nStarting AccessUsersTest: insert unique username");

        User user = new User("UniqueUser", "123456", "What is your favourite " +
                "color", "Red");
        User addedUser = userList.insertNewUser(user);

        assertNotNull(addedUser);
        assertEquals("UniqueUser",addedUser.getUserName());
        assertEquals("123456", addedUser.getPassword());

        System.out.println("Finished AccessUsersTest: insert unique username");
    }

    @Test
    public void testInsertDuplicateUserName()
    {
        System.out.println("\nStarting AccessUsersTest: insert duplicate username");

        User user = new User("User1", "123456", "What is your favourite " +
                "color", "Red");
        User addedUser = userList.insertNewUser(user);
        addedUser = userList.insertNewUser(user);

        assertNull(addedUser);

        System.out.println("Finished insertNewUser: username exist");
    }
}
