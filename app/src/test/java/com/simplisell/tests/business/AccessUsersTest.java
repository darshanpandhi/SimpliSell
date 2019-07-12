package com.simplisell.tests.business;

import com.simplisell.objects.UserAdvertiser;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;
import com.simplisell.tests.persistence.UserPersistenceStub;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccessUsersTest
{
    private AccessUsers accessUsers;
    private UserPersistence userPersistence;

    @Before
    public final void setup()
    {
        userPersistence = mock(UserPersistence.class);
        accessUsers = new AccessUsers(userPersistence);
    }

    @Test
    public void testInsertUniqueUserName()
    {
        System.out.println("\nStarting AccessUsersTest: insert unique username");

        UserAdvertiser userAdvertiser = new UserAdvertiser("Unique Name","UniqueUser",
                "123456", "What is your favourite color?", "Red",
                null, null, null, 0);

        when(userPersistence.getUser("UniqueUser")).thenReturn(null);
        when(userPersistence.insertUserAdvertiser(userAdvertiser)).thenReturn(userAdvertiser);

        UserAdvertiser addedUserAdvertiser = accessUsers.insertNewUserAdvertiser(userAdvertiser);
        assertNotNull(addedUserAdvertiser);
        assertEquals("Unique Name", addedUserAdvertiser.getFirstAndLastName());
        assertEquals("UniqueUser",addedUserAdvertiser.getUserName());
        assertEquals("123456", addedUserAdvertiser.getPassword());
        assertEquals("What is your favourite color?", addedUserAdvertiser.getSecurityQuestion());
        assertEquals("Red", addedUserAdvertiser.getSecurityAnswer());

        verify(userPersistence).getUser("UniqueUser");
        verify(userPersistence).insertUserAdvertiser(userAdvertiser);

        System.out.println("Finished AccessUsersTest: insert unique username");
    }

//    @Test
//    public void testInsertDuplicateUserName()
//    {
//        System.out.println("\nStarting AccessUsersTest: insert duplicate username");
//
//        User user = new User("ba ba","User1", "123456", "What is your favourite " +
//                "color", "Red", null, null, null);
//        when(userPersistence.getUser("User1")).thenReturn(null).thenReturn(user);
//        when(userPersistence.insertUser(user)).thenReturn(user).thenReturn(null);
//        User addedUser = accessUsers.insertNewUser(user);
//        assertNotNull(addedUser);
//
//        verify(userPersistence).getUser("User1");
//        verify(userPersistence).insertUser(user);
//
//        addedUser = accessUsers.insertNewUser(user);
//        assertNull(addedUser);
//
//        System.out.println("Finished insertNewUser: username exist");
//    }
//
//
//    @Test
//    public void testChangePassword()
//    {
//        System.out.println("\nStarting AccessUsersTest: test change password");
//
//        User user = new User("ba ba","User10", "123456", "What is your favourite " +
//                "color", "Red", null, null, null);
//        accessUsers.insertNewUser(user);
//        assertNotNull(user);
//
//        accessUsers.updatePassword("User10", "654321");
//        assertEquals("654321", accessUsers.getUser("User10").getPassword());
//
//        System.out.println("Finished insertNewUser: password changed");
//    }
//
//    @Test
//    public void testUpdateProfileInformation()
//    {
//        System.out.println("\nStarting AccessUsersTest: test update profile information");
//
//        User user = new User("Bobby Lee","Bobby", "123456", "What is your favourite " +
//                "color", "Red", null, null, null);
//        accessUsers.insertNewUser(user);
//        assertNotNull(user);
//
//        String newFullName = "Lee Bobby";
//        String newEmail = "leebobby@yahoo.ca";
//        String newPhoneNumber = "1234567890";
//        String newSecurityQuestion = "What is your favourite movie";
//        String newSecurityAnswer = "Hereditary";
//        accessUsers.updateProfileInformation("Bobby", newFullName, newEmail, newPhoneNumber, newSecurityQuestion, newSecurityAnswer);
//        user = accessUsers.getUser("Bobby");
//        assertEquals(newFullName, user.getFirstAndLastName());
//        assertEquals(newEmail, user.getEmail());
//        assertEquals(newPhoneNumber, user.getPhoneNumber());
//        assertEquals(newSecurityQuestion, user.getSecurityQuestion());
//        assertEquals(newSecurityAnswer, user.getSecurityAnswer());
//
//        System.out.println("Finished insertNewUser: profile information updated");
//    }
//
//    @Test
//    public void testUpdateProfileImage()
//    {
//        System.out.println("\nStarting AccessUsersTest: test update profile photo");
//
//        User user = new User("Bobby Lee 2","Bobby2", "123456", "What is your favourite " +
//                "color", "Red", null, null, null);
//        accessUsers.insertNewUser(user);
//        assertNotNull(user);
//
//        accessUsers.updateProfileImage("Bobby2", "profileImageBase64");
//        assertNotNull(user.getProfilePhoto());
//        assertEquals("profileImageBase64", user.getProfilePhoto());
//
//        System.out.println("Finished insertNewUser: profile photo updated");
//    }
}
