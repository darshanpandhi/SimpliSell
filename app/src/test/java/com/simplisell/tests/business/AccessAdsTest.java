package com.simplisell.tests.business;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotEquals;

import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.business.AccessAds;
import com.simplisell.tests.persistence.AdPersistenceStub;

import java.sql.Date;
import java.util.Calendar;
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


        Ad uniqueAd = new Ad(userName, adType, category, title, description, price);

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

        Ad uniqueAd = new Ad(userName, adType, category, title, description, price);

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
    public void testGetAllAds()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads");

        List<Ad> ads = adPersistence.getAllAds();

        assertNotNull(ads);

        System.out.println("Finished AccessAdsTest: get all ads");
    }

    @Test
    public void testGetAllAdsByCategory()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads by category");

        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1));
        List<Ad> ads = adPersistence.getAllAdsByCategory(Category.ELECTRONICS);

        for (Ad ad : ads)
        {
            assertEquals(ad.getCategory(), Category.ELECTRONICS);
        }

        System.out.println("Finished AccessAdsTest: get all ads by category");
    }

    @Test
    public void testSortPriceAsc()
    {
        System.out.println("\nStarting AccessAdsTest: sort price (ascending)");

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

        System.out.println("Finished AccessAdsTest: sort price (ascending)");
    }

    @Test
    public void testSortPriceDesc()
    {
        System.out.println("\nStarting AccessAdsTest sort price (descending)");


        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10));
        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100));
        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1000));
        List<Ad> ads = adPersistence.sortPriceDesc(adPersistence.getAllAds());
        for (int i = 0; i < ads.size()-1; i++)
        {
            double priceDiff = ads.get(i).getPrice() - ads.get(i+1).getPrice();
            assertTrue(priceDiff >= 0);
        }

        System.out.println("Finished AccessAdsTest: sort price (descending)");
    }


    @Test
    public void testGetUserSpecificAds()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads for specific user");

        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1));
        List<Ad> ads = adPersistence.getUserSpecificAds("test");

        for (Ad ad : ads)
        {
            assertEquals(ad.getAdOwner(), "test");
        }

        System.out.println("Finished AccessAdsTest: get all ads for specific user");
    }

    @Test
    public void testFilterAdsByType()
    {
        System.out.println("\nStarting AccessAdsTest: filter ads by ad type");

        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10));
        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100));
        adPersistence.insertAd(new Ad( "test", AdType.WANTED, Category.ELECTRONICS,
                "test", "test", 2));
        adPersistence.insertAd(new Ad( "test", AdType.WANTED, Category.ELECTRONICS,
                "test", "test", 0));

        List<Ad> offeringAds = adPersistence.filterAdsByType(adPersistence.getAllAds(), AdType.OFFERING);
        List<Ad> wantedAds = adPersistence.filterAdsByType(adPersistence.getAllAds(), AdType.WANTED);

        for (Ad ad : offeringAds)
        {
            assertEquals(AdType.OFFERING, ad.getAdType());
        }

        for (Ad ad : wantedAds)
        {
            assertEquals(AdType.WANTED, ad.getAdType());
        }


        System.out.println("Finished AccessAdsTest: filter ads by ad type");
    }

    @Test
    public void testRemoveExpiredAds()
    {
        System.out.println("\nStarting AccessAdsTest: remove expired ads");

        adPersistence.insertAd(new Ad( "test", AdType.WANTED, Category.ELECTRONICS,
                "test", "test", 0));
        adPersistence.insertAd(new Ad( "test", AdType.WANTED, Category.ELECTRONICS,
                "test", "test", 0));

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -30);

        Date oldDate = new Date(c.getTimeInMillis());
        //Set 3 ads to expire
        adPersistence.getAd(0).setExpiryDate(oldDate);
        adPersistence.getAd(1).setExpiryDate(oldDate);
        adPersistence.getAd(2).setExpiryDate(oldDate);

        int sizeBefore = adPersistence.getAllAds().size();
        adPersistence.removeExpiredAds();
        int sizeAfter = adPersistence.getAllAds().size();

        assertTrue(sizeBefore == sizeAfter + 3);

        System.out.println("Finished AccessAdsTest: remove expired ads");
    }

    @Test
    public void testRepostAd()
    {
        System.out.println("\nStarting AccessAdsTest: repost Ad");

        Ad repostThisAd = adPersistence.getAd(0);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -30);
        Date oldDate = new Date(c.getTimeInMillis());
        repostThisAd.setExpiryDate(oldDate);

        //New Expiry Date
        int daysTillExpired = 21;
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DATE, daysTillExpired);
        Date newDate = new Date(c1.getTimeInMillis());

        adPersistence.repostAd(repostThisAd.getAdId());

        assertNotEquals(oldDate, repostThisAd.getExpiryDate());
        //Make sure the time is close enough...
        assertTrue("Ad was not reposted!", (newDate.getTime() - repostThisAd.getExpiryDate().getTime()) < 1000);

        System.out.println("Finished AccessAdsTest: ad reposted");
    }

    @Test
    public void testUpdateAd()
    {
        System.out.println("\nStarting AccessAdsTest: update Ad (category and description does not change)");

        //Description should be the same
        Ad updateThisAd = adPersistence.getAd(0);
        Category oldCategory = updateThisAd.getCategory();
        String oldTile = updateThisAd.getTitle();
        String oldDescription = updateThisAd.getDescription();
        Double oldPrice = updateThisAd.getPrice();

        String newTitle = "this is a new title!!!";
        double newPrice = 321.23;

        Ad updatedAd = new Ad(updateThisAd.getAdId(), updateThisAd.getAdOwner(), updateThisAd.getAdType(), oldCategory, newTitle, oldDescription, newPrice, null);

        adPersistence.updateAd(updatedAd);

        assertNotEquals(oldTile, updateThisAd.getTitle());
        assertNotEquals(oldPrice, updateThisAd.getPrice());
        assertEquals(oldDescription, updateThisAd.getDescription());
        assertEquals(oldCategory, updateThisAd.getCategory());

        assertEquals(newTitle, updateThisAd.getTitle());
        assertEquals(newPrice, updateThisAd.getPrice(),0.001);

        System.out.println("Finished AccessAdsTest: ad updated (category and description did not change)");
    }
}
