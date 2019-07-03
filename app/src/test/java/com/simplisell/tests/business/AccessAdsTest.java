package com.simplisell.tests.business;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static junit.framework.TestCase.assertNull;

import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.business.AccessAds;
import com.simplisell.tests.persistence.AdPersistenceStub;

import java.util.List;


public class AccessAdsTest
{
    private AccessAds adPersistence;

    @Before
    public final void setup()
    {
        AdPersistenceStub adStub = new AdPersistenceStub();
        adPersistence = new AccessAds(adStub);
    }

    @Test
    public final void testInsertUniqueAd()
    {
        System.out.println("\nStarting AccessAdsTest: insert unique ad");

        String userName = "Marley";
        AdType adType = AdType.OFFERING;
        Category category = Category.JOBS_SERVICES;
        String title = "Test Title";
        String description = "Test Description";
        double price = 200;

        Ad uniqueAd = new Ad(1000, userName, adType, category, title, description, price, 0);

        Ad insertedAd = adPersistence.insertAd(uniqueAd);

        assertNotNull(insertedAd);
        assertEquals(userName, insertedAd.getAdOwner());
        assertEquals(adType, insertedAd.getAdType());
        assertEquals(category, insertedAd.getCategory());
        assertEquals(title, insertedAd.getTitle());
        assertEquals(description, insertedAd.getDescription());
        assertEquals(price, insertedAd.getPrice(), 0);

        System.out.println("Finished AccessAdsTest: insert unique ad");
    }

    @Test
    public final void testInsertDuplicateAd()
    {
        System.out.println("\nStarting AccessAdsTest: insert duplicate ad");

        String userName = "Bob";
        AdType adType = AdType.OFFERING;
        Category category = Category.JOBS_SERVICES;
        String title = "Test Title";
        String description = "Test Description";
        double price = 200;

        Ad ad = new Ad(userName, adType, category, title, description, price);

        Ad insertedAd = adPersistence.insertAd(ad);
        // adding the same ad again
        insertedAd = adPersistence.insertAd(ad);

        assertNull(insertedAd);

        System.out.println("Finished AccessAdsTest: insert duplicate ad");
    }

    @Test
    public final void testInsertNull()
    {
        System.out.println("\nStarting AccessAdsTest: insert null");

        Ad insertedAd = adPersistence.insertAd(null);

        assertNull(insertedAd);

        System.out.println("Finished AccessAdsTest: insert null");
    }

    @Test
    public final void testGetDeleted()
    {
        System.out.println("\nStarting AccessAdsTest: get deleted ad");

        String userName = "John";
        AdType adType = AdType.OFFERING;
        Category category = Category.JOBS_SERVICES;
        String title = "Test Title";
        String description = "Test Description";
        double price = 200;

        Ad uniqueAd = new Ad(12121, userName, adType, category, title, description, price, 10);

        // verifying if we are able to get a deleted ad from the list
        Ad insertedAd = adPersistence.insertAd(uniqueAd);
        Ad removedAd = adPersistence.removeAd(insertedAd);
        Ad foundAd = adPersistence.getAd(removedAd.getAdId());
        assertNull(foundAd);

        System.out.println("Finished AccessAdsTest: get deleted ad");
    }

    @Test
    public final void testRemoveWrongAd()
    {
        System.out.println("\nStarting AccessAdsTest: remove wrong ad");

        String userName = "Bob";
        AdType adType = AdType.OFFERING;
        Category category = Category.JOBS_SERVICES;
        String title = "Test Title";
        String description = "Test Description";
        double price = 200;

        Ad ad = new Ad(userName, adType, category, title, description, price);

        // removing ad not present in the list of adds
        Ad removedAd = adPersistence.removeAd(ad);

        assertNull(removedAd);

        System.out.println("Finished AccessAdsTest: remove wrong ad");
    }

    @Test
    public final void testRemoveNull()
    {
        System.out.println("\nStarting AccessAdsTest: remove null");

        Ad removeAd = adPersistence.removeAd(null);
        assertNull(removeAd);

        System.out.println("Finished AccessAdsTest: remove null");
    }

    @Test
    public final void testReportAd()
    {
        System.out.println("\nStarting AccessAdsTest: report ad");

        int reportsBefore = adPersistence.getAd(0).getNumReports();
        adPersistence.reportAd(0);
        assertEquals(reportsBefore + 1, adPersistence.getAd(0).getNumReports());

        System.out.println("Finished AccessAdsTest: ad reported");
    }

    @Test
    public void testGetAllAds()
    {
        System.out.println("\nStarting testSearch: get all ads");

        List<Ad> ads = adPersistence.getAllAds();

        assertNotNull(ads);

        System.out.println("Finished testSearch: get all ads");
    }

    @Test
    public void testGetAllAdsByCategory()
    {
        System.out.println("\nStarting testSearch: get all ads by category");

        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1));
        List<Ad> ads = adPersistence.getAllAdsByCategory(Category.ELECTRONICS);

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
        List<Ad> ads = adPersistence.sortPriceAsc(adPersistence.getAllAds());

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

        adPersistence.insertAd(new Ad(1, "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10, 0));
        adPersistence.insertAd(new Ad(2, "test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100, 0));
        adPersistence.insertAd(new Ad(3, "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1000, 0));
        List<Ad> ads = adPersistence.sortPriceDesc(adPersistence.getAllAds());
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

        adPersistence.insertAd(new Ad(4, "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1, 0));
        List<Ad> ads = adPersistence.getUserSpecificAds("test");

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

        Ad reportedAd = adPersistence.insertAd(new Ad(5, "test", AdType.OFFERING, Category.BOOKS,
                "reportedAd3Reports", "reportedAd3Reports", 1, 3));

        Ad reportedAd2 = adPersistence.insertAd(new Ad(6, "test", AdType.OFFERING, Category.BOOKS,
                "reportedAd4Reports", "reportedAd4Reports", 1, 4));

        List<Ad> ads = adPersistence.getReportedAds();

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

        List<Ad> ads = adPersistence.getReportedAds();

        int minNumberReports = 3;

        for (Ad ad : ads)
        {
            assertFalse(ad.getNumReports() < minNumberReports);
        }

        System.out.println("Finished testSearch: get all reported ads (less than 3 reports)");
    }
}
