package com.simplisell.tests.objects;

import com.simplisell.objects.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserAdminTest
{
    @Test
    public void testNewUserAdvertiser()
    {
        System.out.println("\nStarting testUserAdmin");

        UserAdmin userAdmin = new UserAdmin("user1", "123456",
                "What is your favourite color", "Red");
        assertNotNull(userAdmin);
        assertEquals("user1",userAdmin.getUserName());
        assertEquals("123456",userAdmin.getPassword());
        assertEquals("What is your favourite color", userAdmin.getSecurityQuestion());
        assertEquals("Red", userAdmin.getSecurityAnswer());
        System.out.println("Finished testUserAdmin");
    }

}