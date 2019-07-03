package com.simplisell.tests.business;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.business.AccessAds;
import com.simplisell.tests.persistence.AdPersistenceStub;


public class AccessAdsTest
{
    private AccessAds accessAds;

    @Before
    public final void setup()
    {
        accessAds = new AccessAds(new AdPersistenceStub());
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

        Ad insertedAd = accessAds.insertAd(uniqueAd);

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

        Ad insertedAd = accessAds.insertAd(ad);
        // adding the same ad again
        insertedAd = accessAds.insertAd(ad);

        assertNull(insertedAd);

        System.out.println("Finished AccessAdsTest: insert duplicate ad");
    }

    @Test
    public final void testInsertNull()
    {
        System.out.println("\nStarting AccessAdsTest: insert null");

        Ad insertedAd = accessAds.insertAd(null);

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
        Ad insertedAd = accessAds.insertAd(uniqueAd);
        Ad removedAd = accessAds.removeAd(insertedAd);
        Ad foundAd = accessAds.getAd(removedAd.getAdId());
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
        Ad removedAd = accessAds.removeAd(ad);

        assertNull(removedAd);

        System.out.println("Finished AccessAdsTest: remove wrong ad");
    }

    @Test
    public final void testRemoveNull()
    {
        System.out.println("\nStarting AccessAdsTest: remove null");

        Ad removeAd = accessAds.removeAd(null);
        assertNull(removeAd);

        System.out.println("Finished AccessAdsTest: remove null");
    }

    @Test
    public final void testReportAd()
    {
        System.out.println("\nStarting AccessAdsTest: report ad");

        Ad reportThisAd = accessAds.getAd(0);
        reportThisAd.incrementNumReports();
        assertEquals(1, reportThisAd.getNumReports());

        System.out.println("Finished AccessAdsTest: ad reported");
    }

    @Test
    public final void testGetAdID()
    {
        System.out.println("\nStarting AccessAdsTest: get new adID");

        accessAds = new AccessAds(new AdPersistenceStub());
        int expectedNewAdID = 5;
        int newAdID = accessAds.getAdID();
        assertEquals(5, newAdID);

        System.out.println("Finished AccessAdsTest: correct adID received");
    }
}