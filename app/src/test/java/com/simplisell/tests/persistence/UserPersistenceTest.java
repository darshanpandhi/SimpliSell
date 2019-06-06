package com.simplisell.tests.persistence;

import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;
import com.simplisell.persistence.stubs.UserPersistenceStub;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

public class UserPersistenceTest {
    @Test
    public void testPersistenceStub()
    {
        System.out.println("Testing user Persistence");

        UserPersistence stub = new UserPersistenceStub();
        List<User> users = stub.getUsers();
        assertNotNull(stub);
        assertNotNull(users);

        System.out.println("Finish user Persistence");
    }

    @Test
    public void testGetValidUser()
    {
        System.out.println("Testing getUser for a valid user");

        UserPersistence stub = new UserPersistenceStub();
        User user = stub.getUser("Bob");
        assertNotNull(user);

        System.out.println("Finish testing getUser for a valid user");
    }

    @Test
    public void testGetInvalidUser()
    {
        System.out.println("Testing getUser for an invalid user");

        UserPersistence stub = new UserPersistenceStub();
        User user = stub.getUser("xxxxxx");
        assertNull(user);

        System.out.println("Finish testing getUser for a invalid user");
    }

    @Test
    public void testGetPasswordFromValidUser()
    {
        System.out.println("Testing getPassword from Valid User");

        UserPersistence stub = new UserPersistenceStub();
        String password = stub.getPassword("Bob");
        assertEquals("1234", password);

        System.out.println("Finish testing getPassword from valid User");
    }

    @Test
    public void testGetPasswordFromInvalidUser()
    {
        System.out.println("Testing getPassword from invalid User");

        UserPersistence stub = new UserPersistenceStub();
        assertNull(stub.getUser("xxxxxx"));
        String password = stub.getPassword("xxxxxx");
        assertNull( password);

        System.out.println("Finish testing getPassword from invalid User");
    }

    @Test
    public void testInsertUser()
    {
        System.out.println("Testing insertUser");

        UserPersistence stub = new UserPersistenceStub();
        List<User> users = stub.getUsers();
        int stubOriginalSize = users.size();

        assertNull(stub.getUser("John"));
        User newUser = new User("John", "aaa");

        stub.insertUser(newUser);
        assertTrue(users.size() == stubOriginalSize + 1);
        assertNotNull(stub.getUser("John"));

        System.out.println("Finish testing insertUser");
    }

    @Test
    public void testUpdatePasswordForValidUser()
    {
        System.out.println("Testing updatePassword for valid user");

        UserPersistence stub = new UserPersistenceStub();
        User currentUser = stub.updatePassword("Bob", "1111");
        assertEquals(currentUser.getPassword(), "1111");

        System.out.println("Finish testing updatePassword for valid user");
    }

    @Test
    public void testUpdatePasswordForInvalidUser()
    {
        System.out.println("Testing updatePassword for invalid user");

        UserPersistence stub = new UserPersistenceStub();
        assertNull(stub.getUser("xxxxxx"));
        User currentUser = stub.updatePassword("xxxxxx", "1111");
        assertNull(currentUser);

        System.out.println("Finish testing updatePassword for invalid user");
    }

}
