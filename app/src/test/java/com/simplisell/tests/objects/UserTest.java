package com.simplisell.tests.objects;

import com.simplisell.objects.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest
{
	@Test
	public void testNewUser()
	{
		User user;

		System.out.println("\nStarting testUser");

		user = new User("go as","user1", "123456", "What is your favourite color", "Red", 0, "user@email.com", "204-512-3213", null) {};
		assertNotNull(user);
		assertEquals("user1",user.getUserName());
		assertEquals("123456",user.getPassword());
		assertEquals("What is your favourite color", user.getSecurityQuestion());
		assertEquals("Red", user.getSecurityAnswer());
		assertEquals(0, user.getNumReports());
		assertEquals("user@email.com", user.getEmail());
		assertEquals("204-512-3213", user.getPhoneNumber());
		assertEquals(null, user.getProfilePhoto());
		System.out.println("Finished testUser");
	}

	@Test
	public void testReportedUser()
	{
		User user;

		System.out.println("\nStarting testReportedUser");

		user = new User("go as","user1", "123456", "What is your favourite color", "Red", 3, "user1@email.com", "204-321-3111", "profileimage") {};
		assertNotNull(user);
		assertEquals("user1",user.getUserName());
		assertEquals("123456",user.getPassword());
		assertEquals("What is your favourite color", user.getSecurityQuestion());
		assertEquals("Red", user.getSecurityAnswer());
		assertTrue(user.getNumReports() == 3);
		assertEquals("user1@email.com", user.getEmail());
		assertEquals("204-321-3111", user.getPhoneNumber());
		assertEquals("profileimage", user.getProfilePhoto());
		System.out.println("Finished testReportedUser");
	}
}