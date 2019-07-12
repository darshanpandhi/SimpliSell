package com.simplisell.tests.business;

import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;
import com.simplisell.tests.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class AccessUsersIT {
    private AccessUsers accessUsers ;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        this.accessUsers = new AccessUsers();
    }

    @Test
    public void testGetUser() {
        final User user;
        System.out.println("\nStarting AccessUsers: testGetUser");

        user = accessUsers.getUser("Bob");
        assertNotNull("User should not be null", user);
        assertTrue("Bob".equals(user.getUserName()));

        System.out.println("Finished test AccessUsers");
    }

    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}
