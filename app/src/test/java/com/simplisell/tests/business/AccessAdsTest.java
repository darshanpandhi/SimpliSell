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
    private AccessAds accessAds;
    private AdPersistenceStub adStub;

    @Before
    public final void setup()
    {
        adStub = new AdPersistenceStub();
        accessAds = new AccessAds(adStub);
    }

    @Test
    public final void testInsertUniqueAd()
    {
        System.out.println("\nStarting AccessAdsTest: insert unique ad");

        int adId = adStub.getNewAdId();
        String userName = "Marley";
        AdType adType = AdType.OFFERING;
        Category category = Category.JOBS_SERVICES;
        String title = "Test Title";
        String description = "Test Description";
        double price = 200;
        Date expiryDate = null;
        int numReports = 0;

        Ad uniqueAd = new Ad(adId, userName, adType, category, title, description, price, expiryDate, numReports);

        Ad insertedAd = accessAds.insertAd(uniqueAd);

        assertNotNull(insertedAd);
        assertEquals(adId, insertedAd.getAdId());
        assertEquals(userName, insertedAd.getAdOwner());
        assertEquals(adType, insertedAd.getAdType());
        assertEquals(category, insertedAd.getCategory());
        assertEquals(title, insertedAd.getTitle());
        assertEquals(description, insertedAd.getDescription());
        assertEquals(price, insertedAd.getPrice(), 0);
        assertEquals(numReports, insertedAd.getNumReports());

        System.out.println("Finished AccessAdsTest: insert unique ad");
    }

    @Test
    public final void testInsertDuplicateAd()
    {
        System.out.println("\nStarting AccessAdsTest: insert duplicate ad");

        int adId = adStub.getNewAdId();
        String userName = "Bob";
        AdType adType = AdType.OFFERING;
        Category category = Category.JOBS_SERVICES;
        String title = "Test Title";
        String description = "Test Description";
        double price = 200;
        Date expiryDate = null;
        int numReports = 0;

        Ad ad = new Ad(adId, userName, adType, category, title, description, price, expiryDate,
                numReports);

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

        int adId = adStub.getNewAdId();
        String userName = "John";
        AdType adType = AdType.OFFERING;
        Category category = Category.JOBS_SERVICES;
        String title = "Test Title";
        String description = "Test Description";
        double price = 200;
        Date expiryDate = null;
        int numReports = 0;

        Ad uniqueAd = new Ad(adId, userName, adType, category, title, description, price, expiryDate, numReports);

        // verifying if we are able to get a deleted ad from the list
        Ad insertedAd = accessAds.insertAd(uniqueAd);
        Ad removedAd = accessAds.removeAd(insertedAd);
        Ad foundAd = accessAds.getAd(removedAd.getAdId());
        assertNull(foundAd);

        System.out.println("Finished AccessAdsTest: get deleted ad");
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
    public void testGetAllAds()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads");

        List<Ad> ads = accessAds.getAllAds();

        assertNotNull(ads);

        System.out.println("Finished AccessAdsTest: get all ads");
    }

    @Test
    public void testGetAllAdsByCategory()
    {
//        System.out.println("\nStarting AccessAdsTest: get all ads by category");
//
//        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
//                "test", "test", 1, null, 0));
//        List<Ad> ads = accessAds.filterAdsByCategory(Category.ELECTRONICS);
//
//        for (Ad ad : ads)
//        {
//            assertEquals(ad.getCategory(), Category.ELECTRONICS);
//        }
//
//        System.out.println("Finished AccessAdsTest: get all ads by category");
    }

    @Test
    public void testSortPriceAsc()
    {
        System.out.println("\nStarting AccessAdsTest: sort price (ascending)");

        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10, null, 0));
        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100, null, 0));
        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1000, null, 0));
        List<Ad> ads = accessAds.sortPriceAsc(accessAds.getAllAds());

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


        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10, null, 0));
        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100, null, 0));
        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1000, null, 0));
        List<Ad> ads = accessAds.sortPriceDesc(accessAds.getAllAds());
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

        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1, null, 0));
        List<Ad> ads = accessAds.getUserSpecificAds("test");

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

        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10, null, 0));
        accessAds.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100, null, 0));
        accessAds.insertAd(new Ad( adStub.getNewAdId(), "test", AdType.WANTED, Category.ELECTRONICS,
                "test", "test", 2, null, 0));
        accessAds.insertAd(new Ad( adStub.getNewAdId(), "test", AdType.WANTED, Category.ELECTRONICS,
                "test", "test", 0, null, 0));

        List<Ad> offeringAds = accessAds.filterAdsByType(AdType.OFFERING);
        List<Ad> wantedAds = accessAds.filterAdsByType(AdType.WANTED);

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

        accessAds.insertAd(new Ad( adStub.getNewAdId(), "test", AdType.WANTED, Category.ELECTRONICS,
                "test", "test", 0, null, 0));
        accessAds.insertAd(new Ad( adStub.getNewAdId(), "test", AdType.WANTED, Category.ELECTRONICS,
                "test", "test", 0, null, 0));

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -30);

        Date oldDate = new Date(c.getTimeInMillis());
        //Set 3 ads to expire
        accessAds.getAd(0).setExpiryDate(oldDate);
        accessAds.getAd(1).setExpiryDate(oldDate);
        accessAds.getAd(2).setExpiryDate(oldDate);

        int sizeBefore = accessAds.getAllAds().size();
        accessAds.removeExpiredAds();
        int sizeAfter = accessAds.getAllAds().size();

        assertTrue(sizeBefore == sizeAfter + 3);

        System.out.println("Finished AccessAdsTest: remove expired ads");
    }

    @Test
    public void testRepostAd()
    {
        System.out.println("\nStarting AccessAdsTest: repost Ad");

        Ad repostThisAd = accessAds.getAd(0);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -30);
        Date oldDate = new Date(c.getTimeInMillis());
        repostThisAd.setExpiryDate(oldDate);

        //New Expiry Date
        int daysTillExpired = 21;
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DATE, daysTillExpired);
        Date newDate = new Date(c1.getTimeInMillis());

        accessAds.repostAd(repostThisAd.getAdId());

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
        Ad updateThisAd = accessAds.getAd(0);
        Category oldCategory = updateThisAd.getCategory();
        String oldTile = updateThisAd.getTitle();
        String oldDescription = updateThisAd.getDescription();
        Double oldPrice = updateThisAd.getPrice();
        Date oldExpiryDate = null;
        int oldNumReports = 0;

        String newTitle = "this is a new title!!!";
        double newPrice = 321.23;

        Ad updatedAd = new Ad(updateThisAd.getAdId(), updateThisAd.getAdOwner(), updateThisAd.getAdType(),
                oldCategory, newTitle, oldDescription, newPrice, oldExpiryDate, oldNumReports);

        accessAds.updateAd(updatedAd);

        assertNotEquals(oldTile, updateThisAd.getTitle());
        assertNotEquals(oldPrice, updateThisAd.getPrice());
        assertEquals(oldDescription, updateThisAd.getDescription());
        assertEquals(oldCategory, updateThisAd.getCategory());
        assertEquals(oldNumReports, updateThisAd.getNumReports());

        assertEquals(newTitle, updateThisAd.getTitle());
        assertEquals(newPrice, updateThisAd.getPrice(),0.001);

        System.out.println("Finished AccessAdsTest: ad updated (category and description did not change)");
    }

    //Use mock
    @Test
    public void testReportAd()
    {

    }

    //Use mock
    @Test
    public void testGetReportedAds()
    {

    }
}
