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
import com.simplisell.tests.persistence.UserPersistenceStub;

public class AccessUsersTest
{
    private AccessUsers userList;


    @Before
    public final void setup()
    {
        userList = new AccessUsers(new UserPersistenceStub());
    }


    @Test
    public void testInsertUniqueUserName()
    {
        System.out.println("\nStarting AccessUsersTest: insert unique username");

        User user = new User("Bob Saget", "UniqueUser", "123456", "What is your favourite color?", "Red", null, null,
                false);
        User addedUser = userList.insertNewUser(user);

        assertNotNull(addedUser);
        assertEquals("Bob Saget", addedUser.getFirstAndLastName());
        assertEquals("UniqueUser", addedUser.getUserName());
        assertEquals("123456", addedUser.getPassword());
        assertEquals("What is your favourite color?", addedUser.getSecurityQuestion());
        assertEquals("Red", addedUser.getSecurityAnswer());

        System.out.println("Finished AccessUsersTest: insert unique username");
    }


    @Test
    public void testInsertDuplicateUserName()
    {
        System.out.println("\nStarting AccessUsersTest: insert duplicate username");

        User user = new User("ba ba", "User1", "123456", "What is your favourite " + "color", "Red", null, null, false);
        User addedUser = userList.insertNewUser(user);
        addedUser = userList.insertNewUser(user);

        assertNull(addedUser);

        System.out.println("Finished insertNewUser: username exist");
    }


    @Test
    public void testChangePassword()
    {
        System.out.println("\nStarting AccessUsersTest: test change password");

        User user = new User("ba ba", "User10", "123456", "What is your favourite " + "color", "Red", null, null,
                false);
        userList.insertNewUser(user);
        assertNotNull(user);

        userList.updatePassword("User10", "654321");
        assertEquals("654321", userList.getUser("User10").getPassword());

        System.out.println("Finished insertNewUser: password changed");
    }


    @Test
    public void testUpdateProfileInformation()
    {
        System.out.println("\nStarting AccessUsersTest: test update profile information");

        User user = new User("Bobby Lee", "Bobby", "123456", "What is your favourite " + "color", "Red", null, null,
                false);
        userList.insertNewUser(user);
        assertNotNull(user);
        String newFullName = "Lee Bobby";
        String newEmail = "leebobby@yahoo.ca";
        String newPhoneNumber = "1234567890";
        String newSecurityQuestion = "What is your favourite movie";
        String newSecurityAnswer = "Hereditary";
        userList.updateProfileInformation("Bobby", newFullName, newEmail, newPhoneNumber, newSecurityQuestion,
                newSecurityAnswer);
        user = userList.getUser("Bobby");
        assertEquals(newFullName, user.getFirstAndLastName());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newPhoneNumber, user.getPhoneNumber());
        assertEquals(newSecurityQuestion, user.getSecurityQuestion());
        assertEquals(newSecurityAnswer, user.getSecurityAnswer());

        System.out.println("Finished insertNewUser: profile information updated");
    }


    @Test
    public void testRemoveUser()
    {
        System.out.println("\nStarting AccessUsersTest: test removeUser");
        User user = new User("Jay Mat", "RemoveThisUser", "123456", "What is your favourite " + "color", "Red", null,
                null, false);
        userList.insertNewUser(user);
        assertNotNull("User not inserted", userList.getUser("RemoveThisUser"));
        userList.deleteUser("RemoveThisUser");
        assertNull("User not deleted", userList.getUser("RemoveThisUser"));
        System.out.println("Finished AccessUsersTest: test removeUser");
    }
}
