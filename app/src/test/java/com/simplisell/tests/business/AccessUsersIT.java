package com.simplisell.tests.business;

import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;
import com.simplisell.persistence.hsqldb.UserPersistenceHSQLDB;
import com.simplisell.tests.utils.TestUtils;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class AccessUsersIT {
    private AccessUsers accessUsers ;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        UserPersistence userPersistence = new UserPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        accessUsers = new AccessUsers(userPersistence);
    }

    @Test
    public void testGetUser()
    {
        User user;
        System.out.println("\nStarting AccessUsersIT: testGetUser");

        user = accessUsers.getUser("Bob");
        assertNotNull("User should not be null", user);
        assertTrue("Bob".equals(user.getUserName()));

        System.out.println("Finished AccessUsersIT: testGetUser");
    }

    @Test
    public void testInsertUser()
    {
        System.out.println("\nStarting AccessUsersIT: testInsertUser");
        User insertedUser = new User("fullName", "insertedUserName", "123456", "What is your favourite color?", "Red", null, null, false);
        insertedUser = accessUsers.insertNewUser(insertedUser);
        assertNotNull(insertedUser);
        assertNotNull(accessUsers.getUser("insertedUserName"));
        System.out.println("Finished AccessUsersIT: testInsertUser");
    }

    @Test
    public void testUpdatePassword()
    {
        System.out.println("\nStarting AccessUsersIT: testUpdatePassword");
        User user = accessUsers.getUser("Bob");
        assertNotNull(user);
        assertEquals("123456", user.getPassword());
        accessUsers.updatePassword("Bob", "111111");
        user = accessUsers.getUser("Bob");
        assertEquals("111111", user.getPassword());
        System.out.println("Finished AccessUsersIT: testUpdatePassword");
    }

    @Test
    public void testUpdateProfileInformation()
    {
        System.out.println("\nStarting AccessUsersIT: testUpdateProfileInformation");
        User user = accessUsers.getUser("Bob");
        assertNotNull(user);
        String newFullName = "Bob Saget";
        String newEmail = "bob@email.com";
        String newPhoneNumber = "12345678";
        String newSecurityAnswer = "Green";
        accessUsers.updateProfileInformation("Bob", newFullName, newEmail, newPhoneNumber, "What is your favourite color?", "Green");
        user = accessUsers.getUser("Bob");
        assertEquals(newFullName, user.getFirstAndLastName());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newPhoneNumber, user.getPhoneNumber());
        assertEquals("What is your favourite color?", user.getSecurityQuestion());
        assertEquals(newSecurityAnswer, user.getSecurityAnswer());
        System.out.println("Finished AccessUsersIT: testUpdateProfileInformation");
    }

    @Test
    public void testRemoveUser()
    {
        System.out.println("\nStarting AccessUsersTest: test removeUser");
        User user = new User("Jay Mat","RemoveThisUser", "123456", "What is your favourite " +
                "color", "Red", null, null, false);
        accessUsers.insertNewUser(user);
        TestCase.assertNotNull("User not inserted", accessUsers.getUser("RemoveThisUser"));
        accessUsers.deleteUser("RemoveThisUser");
        assertNull("User not deleted", accessUsers.getUser("RemoveThisUser"));
        System.out.println("Finished AccessUsersTest: test removeUser");
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}
