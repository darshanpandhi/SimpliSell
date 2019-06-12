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

		user = new UserAdvertiser("user1", "123456");
		assertNotNull(user);
		assertEquals("user1",user.getUserName());
		assertEquals("123456",user.getPassword());

		System.out.println("Finished testUser");
	}

	@Test
	public void testUserAdvertiser()
	{
		System.out.println("\nStarting testUserAdvertiser");

		User user = new UserAdvertiser("user1", "123456");
		assertNotNull(user);
		assertEquals("user1",user.getUserName());
		assertEquals("123456",user.getPassword());


		System.out.println("Finished testUserAdvertiser");
	}

	@Test
	public void testAddNewUserAd()
	{
		System.out.println("\nStarting testAddNewUserAd");

		UserAdvertiser user = new UserAdvertiser("user1", "123456");
		assertNotNull(user);

		Ad testAd = new Ad(user.getUserName(), AdType.OFFERING, Category.OTHERS, "TestAd Title",
				"TestAd Description", 500);
		user.addNewAd(testAd);

		List<Ad> userAds = user.getPostings();
		assertTrue(user.getPostings().size() == 1);
		assertTrue(userAds.contains("TestAd"));
		System.out.println("Finished testAddNewUserAd");
	}

	@Test
	public void testDeleteValidUserAd()
	{
		System.out.println("\nStarting testDeleteValidUserAd");

		UserAdvertiser user = new UserAdvertiser("user1", "123456");
		assertNotNull(user);

		Ad testAd = new Ad(user.getUserName(), AdType.OFFERING, Category.OTHERS, "TestAd Title",
				"TestAd Description", 500);
		user.addNewAd(testAd);

		List<Ad> userAds = user.getPostings();
		assertTrue(userAds.size() == 1);
		assertTrue(userAds.contains("TestAd"));
		user.deleteAd("TestAd");
		assertTrue(userAds.size() == 0);
		assertFalse(userAds.contains("TestAd"));

		System.out.println("Finished testDeleteValidUserAd");
	}

	@Test
	public void testDeleteInvalidUserAd()
	{
		System.out.println("\nStarting testDeleteInvalidUserAd");

		UserAdvertiser user = new UserAdvertiser("user1", "123456");
		List<Ad> userAds = user.getPostings();
		assertNotNull(user);

		Ad testAd = new Ad(user.getUserName(), AdType.OFFERING, Category.OTHERS, "TestAd Title",
				"TestAd Description", 500);
		user.addNewAd(testAd);

		assertTrue(userAds.size() == 1);
		user.deleteAd("Invalid Ad");
		assertTrue(userAds.size() == 1);
		assertTrue(userAds.contains("TestAd"));

		System.out.println("Finished testDeleteInvalidUserAd");
	}
}