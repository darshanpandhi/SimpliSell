package com.simplisell.tests.objects;

import org.junit.Test;
import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest
{
	@Test
	public void testNewUser()
	{
		User user;

		System.out.println("\nStarting testUser");

		user = new User("user1", "1234");
		assertNotNull(user);
		assertTrue("user1".equals(user.getUserName()));
		assertTrue("1234".equals(user.getPassword()));

		System.out.println("Finished testStudent");
	}

	@Test
	public void testUserAdvertiser()
	{
		User user;
		List<String> userPostings = new ArrayList<>();

		System.out.println("\nStarting testUser");

		userPostings.add("Ad 1");
		user = new UserAdvertiser("user1", "1234", userPostings);
		assertNotNull(user);
		assertNotNull(userPostings);
		assertTrue("user1".equals(user.getUserName()));
		assertTrue("1234".equals(user.getPassword()));
		List<String> userAds = ((UserAdvertiser) user).getPostings();
		assertTrue(userPostings.get(0).equals(userAds.get(0)));

		System.out.println("Finished testStudent");
	}
}