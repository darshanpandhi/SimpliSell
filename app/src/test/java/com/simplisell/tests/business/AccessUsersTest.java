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

        System.out.println("Finished AccessUsersTest: insert duplicate username");
    }

    @Test
    public void testCorrectPassword()
    {
        System.out.println("\nStarting AccessUsersTest: correct password");

        User user = new User("User1", "123456", "What is your favourite " +
                "color", "Red");
        User addedUser = userList.insertNewUser(user);
        String passwordInput = "123456";

        assertTrue(userList.correctPassword("User1", passwordInput));

        System.out.println("Finished AccessUsersTest: correct password");
    }

    @Test
    public void testIncorrectPassword()
    {
        System.out.println("\nStarting AccessUsersTest: incorrect password");

        User user = new User("User1", "123456", "What is your favourite " +
                "color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "User1";
        String passwordInput = "1234567";

        assertFalse(userList.correctPassword(userNameInput, passwordInput));

        System.out.println("Finished AccessUsersTest: incorrect password");
    }

    @Test
    public void testCorrectSecurityAnswer()
    {
        System.out.println("\nStarting AccessUsersTest: correct answer for security question");

        User user = new User("User1", "123456", "What is your favourite " +
                "color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "User1";
        String securityQuestionAnswer = "Red";

        assertTrue(userList.correctSecurityAnswer(userNameInput, securityQuestionAnswer));

        System.out.println("Finished AccessUsersTest: correct answer for security question");
    }

    @Test
    public void testIncorrectSecurityAnswer()
    {
        System.out.println("\nStarting AccessUsersTest: incorrect answer for  security question");

        User user = new User("User1", "123456", "What is your favourite " +
                "color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "User1";
        String securityQuestionAnswer = "Green";

        assertFalse(userList.correctSecurityAnswer(userNameInput, securityQuestionAnswer));

        System.out.println("Finished AccessUsersTest: incorrect answer for  security question");
    }

    @Test
    public void userNameNotFound()
    {
        System.out.println("\nStarting AccessUsersTest: username not found");

        User user = new User("User1", "123456", "What is your favourite " +
                "color", "Red");
        User addedUser = userList.insertNewUser(user);
        String userNameInput = "InvalidUser";
        String passwordInput = "123456";

        assertFalse(userList.correctPassword(userNameInput, passwordInput));

        System.out.println("Finished AccessUsersTest: username not found");
    }
}
