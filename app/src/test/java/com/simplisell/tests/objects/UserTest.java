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

		user = new User("go as","user1", "123456", "What is your favourite color", "Red", "user@email.com", "204-512-3213") {};
		assertNotNull(user);
		assertEquals("user1",user.getUserName());
		assertEquals("123456",user.getPassword());
		assertEquals("What is your favourite color", user.getSecurityQuestion());
		assertEquals("Red", user.getSecurityAnswer());
		assertEquals("user@email.com", user.getEmail());
		assertEquals("204-512-3213", user.getPhoneNumber());
		System.out.println("Finished testUser");
	}

}