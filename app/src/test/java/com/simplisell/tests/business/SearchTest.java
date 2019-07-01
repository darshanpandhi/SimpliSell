package com.simplisell.tests.business;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import com.simplisell.business.AccessUsers;
import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.business.Search;
import com.simplisell.business.AccessAds;
import com.simplisell.objects.User;

public class SearchTest
{

    private Search search;
    private AccessAds adPersistence;
    private AccessUsers userPersistence;

    @Before
    public final void setup()
    {
        search = new Search();
        adPersistence = new AccessAds();
        userPersistence = new AccessUsers();
    }

    @Test
    public void testGetAllAds()
    {
        System.out.println("\nStarting testSearch: get all ads");

        List<Ad> ads = search.getAllAds();

        assertNotNull(ads);

        System.out.println("Finished testSearch: get all ads");
    }

    @Test
    public void testGetAllUsers()
    {
        System.out.println("\nStarting testSearch: get all users");

        List<User> users = search.getAllUsers();

        assertNotNull(users);

        System.out.println("Finished testSearch: get all users");
    }

    @Test
    public void testGetAllAdsByCategory()
    {
        System.out.println("\nStarting testSearch: get all ads by category");

        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1));
        List<Ad> ads = search.getAllAdsByCategory(Category.ELECTRONICS);

        for (Ad ad : ads)
        {
            assertEquals(ad.getCategory(), Category.ELECTRONICS);
        }

        System.out.println("Finished testSearch: get all ads by category");
    }

    @Test
    public void testSortPriceAsc()
    {
        System.out.println("\nStarting testSearch: sort price (ascending)");

        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10));
        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100));
        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1000));
        List<Ad> ads = search.sortPriceAsc(search.getAllAds());

        for (int i = 0; i < ads.size()-1; i++)
        {
            double priceDiff = ads.get(i+1).getPrice() - ads.get(i).getPrice();
            assertTrue(priceDiff >= 0);
        }

        System.out.println("Finished testSearch: sort price (ascending)");
    }

    @Test
    public void testSortPriceDesc()
    {
        System.out.println("\nStarting testSearch sort price (descending)");

        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10));
        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100));
        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1000));
        List<Ad> ads = search.sortPriceDesc(search.getAllAds());
        for (int i = 0; i < ads.size()-1; i++)
        {
            double priceDiff = ads.get(i).getPrice() - ads.get(i+1).getPrice();
            assertTrue(priceDiff >= 0);
        }

        System.out.println("Finished testSearch: sort price (descending)");
    }


    @Test
    public void testGetUserSpecificAds()
    {
        System.out.println("\nStarting testSearch: get all ads for specific user");

        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1));
        List<Ad> ads = search.getUserSpecificAds("test");

        for (Ad ad : ads)
        {
            assertEquals(ad.getAdOwner(), "test");
        }

        System.out.println("Finished testSearch: get all ads for specific user");
    }

    @Test
    public void testGetReportedAdsMoreThan3Reports()
    {
        System.out.println("\nStarting testSearch: get all reported ads (3 or more reports)");

        Ad reportedAd = adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.BOOKS,
                "reportedAd3Reports", "reportedAd3Reports", 1));
        reportedAd.incrementNumReports();
        reportedAd.incrementNumReports();
        reportedAd.incrementNumReports();

        reportedAd = adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.BOOKS,
                "reportedAd4Reports", "reportedAd4Reports", 1));
        reportedAd.incrementNumReports();
        reportedAd.incrementNumReports();
        reportedAd.incrementNumReports();
        reportedAd.incrementNumReports();

        List<Ad> ads = search.getReportedAds();

        int minNumberReports = 3;

        for (Ad ad : ads)
        {
            assertTrue(ad.getNumReports() >= minNumberReports);
        }

        assertTrue(ads.size() == 2);

        System.out.println("Finished testSearch: get all reported ads (3 or more reports)");
    }

    @Test
    public void testGetReportedAdsLessThan3Reports()
    {
        System.out.println("\nStarting testSearch: get all reported ads (less than 3 reports)");

        List<Ad> ads = search.getReportedAds();

        int minNumberReports = 3;

        for (Ad ad : ads)
        {
            assertFalse(ad.getNumReports() < minNumberReports);
        }

        System.out.println("Finished testSearch: get all reported ads (less than 3 reports)");
    }

    @Test
    public void testGetReportedUsersMoreThan3Reports()
    {
        System.out.println("\nStarting testSearch: get all reported users (3 or more reports)");

        User reportedUser = userPersistence.insertNewUser(new User("Bad User", "testUser", "123456", "What is your favourite color", "Black", 0));
        reportedUser.incrementNumReports();
        reportedUser.incrementNumReports();
        reportedUser.incrementNumReports();

        reportedUser = userPersistence.insertNewUser(new User("Worst User", "testUser1", "123456", "What is your favourite color", "Black", 0));
        reportedUser.incrementNumReports();
        reportedUser.incrementNumReports();
        reportedUser.incrementNumReports();
        reportedUser.incrementNumReports();

        List<User> users = search.getReportedUsers();

        int minNumberReports = 3;

        for (User user : users)
        {
            assertTrue(user.getNumReports() >= minNumberReports);
        }

        assertTrue(users.size() == 2);

        System.out.println("Finished testSearch: get all reported ads (3 or more reports)");
    }
}
