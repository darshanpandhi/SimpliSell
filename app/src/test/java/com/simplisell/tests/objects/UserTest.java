package com.simplisell.tests.objects;

import com.simplisell.objects.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest
{
	@Test
	public void testNewAdvitiserUser()
	{
		System.out.println("\nStarting testUser");

		User User = new User("go as","user1",
				"123456", "What is your favourite color", "Red",
				"user@email.com", "204-512-3213", false);
		assertNotNull(User);
		assertEquals("user1",User.getUserName());
		assertEquals("123456",User.getPassword());
		assertEquals("What is your favourite color", User.getSecurityQuestion());
		assertEquals("Red", User.getSecurityAnswer());
		assertEquals("user@email.com", User.getEmail());
		assertEquals("204-512-3213", User.getPhoneNumber());
		System.out.println("Finished testUser");
	}

	@Test
	public void testAdminUser()
	{
		System.out.println("\nStarting testUserAdmin");

		User userAdmin = new User("bad admin","user1", "123456",
				"What is your favourite color", "Red","","",true);
		assertNotNull(userAdmin);
		assertEquals("user1",userAdmin.getUserName());
		assertEquals("123456",userAdmin.getPassword());
		assertEquals("What is your favourite color", userAdmin.getSecurityQuestion());
		assertEquals("Red", userAdmin.getSecurityAnswer());
		System.out.println("Finished testUserAdmin");
	}

}