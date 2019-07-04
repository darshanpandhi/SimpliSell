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

        User user = new User("Bob Saget","UniqueUser", "123456", "What is your favourite " +
                "color", "Red", 0, null, null, null);
        User addedUser = userList.insertNewUser(user);

        assertNotNull(addedUser);
        assertEquals("UniqueUser",addedUser.getUserName());
        assertEquals("123456", addedUser.getPassword());
        assertEquals(0, addedUser.getNumReports());
        System.out.println("Finished AccessUsersTest: insert unique username");
    }

    @Test
    public void testInsertDuplicateUserName()
    {
        System.out.println("\nStarting AccessUsersTest: insert duplicate username");

        User user = new User("ba ba","User1", "123456", "What is your favourite " +
                "color", "Red", 0, null, null, null);
        User addedUser = userList.insertNewUser(user);
        addedUser = userList.insertNewUser(user);

        assertNull(addedUser);

        System.out.println("Finished insertNewUser: username exist");
    }

    @Test
    public void testReportUser()
    {
        System.out.println("\nStarting AccessUsersTest: test report user");

        User user = new User("ba ba","User100", "123456", "What is your favourite " +
                "color", "Red", 0, null, null, null);
        userList.insertNewUser(user);
        assertNotNull(user);

        userList.reportUser("User100");
        assertEquals(1, userList.getUser("User100").getNumReports());

        System.out.println("Finished insertNewUser: user reported");
    }

    @Test
    public void testChangePassword()
    {
        System.out.println("\nStarting AccessUsersTest: test change password");

        User user = new User("ba ba","User10", "123456", "What is your favourite " +
                "color", "Red", 0, null, null, null);
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

        User user = new User("Bobby Lee","Bobby", "123456", "What is your favourite " +
                "color", "Red", 0, null, null, null);
        userList.insertNewUser(user);
        assertNotNull(user);

        String newFullName = "Lee Bobby";
        String newEmail = "leebobby@yahoo.ca";
        String newPhoneNumber = "1234567890";
        String newSecurityQuestion = "What is your favourite movie";
        String newSecurityAnswer = "Hereditary";
        userList.updateProfileInformation("Bobby", newFullName, newEmail, newPhoneNumber, newSecurityQuestion, newSecurityAnswer);
        user = userList.getUser("Bobby");
        assertEquals(newFullName, user.getFirstAndLastName());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newPhoneNumber, user.getPhoneNumber());
        assertEquals(newSecurityQuestion, user.getSecurityQuestion());
        assertEquals(newSecurityAnswer, user.getSecurityAnswer());

        System.out.println("Finished insertNewUser: profile information updated");
    }

    @Test
    public void testUpdateProfileImage()
    {
        System.out.println("\nStarting AccessUsersTest: test update profile photo");

        User user = new User("Bobby Lee 2","Bobby2", "123456", "What is your favourite " +
                "color", "Red", 0, null, null, null);
        userList.insertNewUser(user);
        assertNotNull(user);

        String newFullName = "Lee Bobby";
        String newEmail = "leebobby@yahoo.ca";
        String newPhoneNumber = "1234567890";
        String newSecurityQuestion = "What is your favourite movie";
        String newSecurityAnswer = "Hereditary";
        userList.updateProfileImage("Bobby2", "profileImageBase64");
        assertNotNull(user.getProfilePhoto());
        assertEquals("profileImageBase64", user.getProfilePhoto());
        System.out.println("Finished insertNewUser: profile photo updated");
    }
}
