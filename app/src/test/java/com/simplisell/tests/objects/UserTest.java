package com.simplisell.tests.objects;

import org.junit.Test;
import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest
{
	@Test
	public void testNewUser()
	{
		User user;

		System.out.println("\nStarting testUser");

		user = new UserAdvertiser("user1", "1234");
		assertNotNull(user);
		assertEquals("user1",user.getUserName());
		assertEquals("1234",user.getPassword());

		System.out.println("Finished testStudent");
	}

	@Test
	public void testUserAdvertiser()
	{
		System.out.println("\nStarting testUserAdvertiser");

		User user = new UserAdvertiser("user1", "1234");
		assertNotNull(user);
		assertEquals("user1",user.getUserName());
		assertEquals("1234",user.getPassword());


		System.out.println("Finished testUserAdvertiser");
	}

	@Test
	public void testAddNewUserAd()
	{
		System.out.println("\nStarting testAddNewUserAd");

		UserAdvertiser user = new UserAdvertiser("user1", "1234");
		assertNotNull(user);
		user.addNewAd("TestAd");
		List<String> userAds = user.getPostings();
		assertTrue(user.getPostings().size() == 1);
		assertTrue(userAds.contains("TestAd"));
		System.out.println("Finished testAddNewUserAd");
	}

	@Test
	public void testDeleteValidUserAd()
	{
		System.out.println("\nStarting testDeleteValidUserAd");

		UserAdvertiser user = new UserAdvertiser("user1", "1234");
		assertNotNull(user);
		user.addNewAd("TestAd");
		List<String> userAds = user.getPostings();
		assertTrue(userAds.size() == 1);
		assertTrue(userAds.contains("TestAd"));
		user.deleteAd("TestAd");
		assertTrue(userAds.size() == 0);
		assertFalse(userAds.contains("TestAd"));

		System.out.println("Starting testDeleteValidUserAd");
	}

	@Test
	public void testDeleteInvalidUserAd()
	{
		System.out.println("\nStarting testDeleteInvalidUserAd");

		UserAdvertiser user = new UserAdvertiser("user1", "1234");
		List<String> userAds = user.getPostings();
		assertNotNull(user);
		user.addNewAd("TestAd");
		assertTrue(userAds.size() == 1);
		user.deleteAd("Invalid Ad");
		assertTrue(userAds.size() == 1);
		assertTrue(userAds.contains("TestAd"));

		System.out.println("Starting testDeleteInvalidUserAd");
	}
}