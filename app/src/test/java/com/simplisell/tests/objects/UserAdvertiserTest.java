package com.simplisell.tests.objects;

import com.simplisell.objects.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserAdvertiserTest
{
	@Test
	public void testNewUserAdvertiser()
	{
		System.out.println("\nStarting testUserAdvertiser");

		UserAdvertiser userAdvertiser = new UserAdvertiser("go as","user1",
				"123456", "What is your favourite color", "Red",
				"user@email.com", "204-512-3213", 0);
		assertNotNull(userAdvertiser);
		assertEquals("user1",userAdvertiser.getUserName());
		assertEquals("123456",userAdvertiser.getPassword());
		assertEquals("What is your favourite color", userAdvertiser.getSecurityQuestion());
		assertEquals("Red", userAdvertiser.getSecurityAnswer());
		assertEquals("user@email.com", userAdvertiser.getEmail());
		assertEquals("204-512-3213", userAdvertiser.getPhoneNumber());
		assertEquals(0, userAdvertiser.getNumReports());
		System.out.println("Finished testUserAdvertiser");
	}

}