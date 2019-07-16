package com.simplisell.tests.business;

import com.simplisell.business.AccessAds;
import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.persistence.hsqldb.AdPersistenceHSQLDB;
import com.simplisell.persistence.hsqldb.PersistenceException;
import com.simplisell.tests.utils.TestUtils;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class AccessAdsIT {
    private AccessAds accessAds ;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB = TestUtils.copyDB();
        AdPersistence adPersistence = new AdPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        accessAds = new AccessAds(adPersistence);
    }

    @Test
    public final void testInsertUniqueAd()
    {
        System.out.println("\nStarting AccessAdsTestIT: insert unique ad");

        int adId = accessAds.getNewAdId();
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

        TestCase.assertNotNull(insertedAd);
        assertEquals(adId, insertedAd.getAdId());
        assertEquals(userName, insertedAd.getAdOwner());
        assertEquals(adType, insertedAd.getAdType());
        assertEquals(category, insertedAd.getCategory());
        assertEquals(title, insertedAd.getTitle());
        assertEquals(description, insertedAd.getDescription());
        assertEquals(price, insertedAd.getPrice(), 0);
        assertEquals(numReports, insertedAd.getNumReports());

        System.out.println("Finished AccessAdsTestIT: insert unique ad");
    }

    @Test (expected = PersistenceException.class)
    public final void testInsertDuplicateAd()
    {
        System.out.println("\nStarting AccessAdsTestIT: insert duplicate ad");

        int adId = accessAds.getNewAdId();
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

        System.out.println("Finished AccessAdsTestIT: insert duplicate ad");
    }

    @Test
    public void testGetAllAds()
    {
        System.out.println("\nStarting AccessAdsTestIT: get all ads");

        List<Ad> ads = accessAds.getAllAds();

        assertNotNull(ads);

        System.out.println("Finished AccessAdsTestIT: get all ads");
    }

    @Test
    public void testGetAd()
    {
        System.out.println("\nStarting AccessAdsTestIT: get ad");
        int adId = 1;
        Ad ad = accessAds.getAd(adId);
        assertNotNull(ad);
        assertEquals(adId, ad.getAdId());
        System.out.println("Finished AccessAdsTestIT: get ad");
    }

    @Test (expected = PersistenceException.class)
    public void testRemoveAd()
    {
        System.out.println("\nStarting AccessAdsTestIT: remove ad");
        int adId = 1;
        Ad removedAd = accessAds.getAd(adId);
        assertNotNull(removedAd);
        assertEquals(adId, removedAd.getAdId());

        removedAd = accessAds.removeAd(removedAd);

        assertEquals(adId, removedAd.getAdId());
        Ad checkAd = accessAds.getAd(1);        // Should throw an exception since you ad does not exist
        assertNull(checkAd);
        System.out.println("Finished AccessAdsTestIT: remove ad");
    }

    @Test
    public void testUpdateAd()
    {
        System.out.println("\nStarting AccessAdsTestIT: update Ad (category and description does not change)");

        //Description should be the same
        Ad updateThisAd = accessAds.getAd(0);
        Category oldCategory = updateThisAd.getCategory();
        String oldTile = updateThisAd.getTitle();
        String oldDescription = updateThisAd.getDescription();
        Double oldPrice = updateThisAd.getPrice();
        Date oldExpiryDate = null;
        int oldNumReports = 1;

        String newTitle = "this is a new title!!!";
        double newPrice = 321.23;

        Ad updatedAd = new Ad(updateThisAd.getAdId(), updateThisAd.getAdOwner(), updateThisAd.getAdType(),
                oldCategory, newTitle, oldDescription, newPrice, oldExpiryDate, oldNumReports);

        accessAds.updateAd(updatedAd);
        updateThisAd = accessAds.getAd(0);

        assertNotEquals(oldTile, updateThisAd.getTitle());
        assertNotEquals(oldPrice, updateThisAd.getPrice());
        assertEquals(oldDescription, updateThisAd.getDescription());
        assertEquals(oldCategory, updateThisAd.getCategory());
        assertEquals(oldNumReports, updateThisAd.getNumReports());

        assertEquals(newTitle, updateThisAd.getTitle());
        assertEquals(newPrice, updateThisAd.getPrice(),0.001);

        System.out.println("Finished AccessAdsTestIT: ad updated (category and description did not change)");
    }

    @Test
    public void testRepostAd()
    {
        System.out.println("\nStarting AccessAdsTestIT: repost Ad");

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

        repostThisAd = accessAds.getAd(repostThisAd.getAdId());
        assertNotEquals(oldDate, repostThisAd.getExpiryDate());
        //Make sure the time is close enough...
        assertTrue("Ad was not reposted!", (newDate.getTime() - repostThisAd.getExpiryDate().getTime()) < 100000000);
        System.out.println("Finished AccessAdsTestIT: ad reposted");
    }

    @Test
    public void testReportAd()
    {
        System.out.println("\nStarting AccessAdsTestIT: reporting Ad");
        Ad ad = accessAds.getAd(1);
        assertNotNull(ad);
        int numReports = ad.getNumReports();
        System.out.println(numReports);
        accessAds.reportAd(1);
        ad = accessAds.getAd(1);
        assertEquals(numReports + 1, ad.getNumReports());
        System.out.println("Finished AccessAdsTestIT: ad reported");
    }

    @Test
    public void testGetReportedAds()
    {
        System.out.println("\nStarting AccessAdsTestIT: getting reported Ads");
        List<Ad> reportedAds = accessAds.getReportedAds();
        assertNotNull(reportedAds);
        for (int i = 0; i < reportedAds.size(); i++){
            assertTrue(reportedAds.get(i).getNumReports() > 0);
        }
        System.out.println("\nStarting AccessAdsTestIT: reporting Ad have been received");
    }

    @Test (expected = NullPointerException.class)
    public final void testInsertNull()
    {
        System.out.println("\nStarting AccessAdsTest: insert null");

        Ad insertedAd = accessAds.insertAd(null);

        assertNull(insertedAd);

        System.out.println("Finished AccessAdsTest: insert null");
    }

    @Test
    public void testGetAllAdsByCategory()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads by category");

        accessAds.insertAd(new Ad(accessAds.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1, null, 0));
        List<Ad> ads = accessAds.getAllAdsByCategory(Category.ELECTRONICS);

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

        accessAds.insertAd(new Ad(accessAds.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10, null, 0));
        accessAds.insertAd(new Ad(accessAds.getNewAdId(), "test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100, null, 0));
        accessAds.insertAd(new Ad(accessAds.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1000, null, 0));
        List<Ad> ads = accessAds.sortPriceAsc(accessAds.getAllAds());

        for (int i = 0; i < ads.size()-1; i++)
        {
            double priceDiff = ads.get(i+1).getPrice() - ads.get(i).getPrice();
            TestCase.assertTrue(priceDiff >= 0);
        }

        System.out.println("Finished AccessAdsTest: sort price (ascending)");
    }

    @Test
    public void testSortPriceDesc()
    {
        System.out.println("\nStarting AccessAdsTest sort price (descending)");


        accessAds.insertAd(new Ad(accessAds.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 10, null, 0));
        accessAds.insertAd(new Ad(accessAds.getNewAdId(), "test", AdType.OFFERING, Category.OTHERS,
                "test", "test", 100, null, 0));
        accessAds.insertAd(new Ad(accessAds.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1000, null, 0));
        List<Ad> ads = accessAds.sortPriceDesc(accessAds.getAllAds());
        for (int i = 0; i < ads.size()-1; i++)
        {
            double priceDiff = ads.get(i).getPrice() - ads.get(i+1).getPrice();
            TestCase.assertTrue(priceDiff >= 0);
        }

        System.out.println("Finished AccessAdsTest: sort price (descending)");
    }


    @Test
    public void testGetUserSpecificAds()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads for specific user");

        accessAds.insertAd(new Ad(accessAds.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS,
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
    
    @After
    public void tearDown() {
        // reset DB
        this.tempDB.delete();
    }

}
