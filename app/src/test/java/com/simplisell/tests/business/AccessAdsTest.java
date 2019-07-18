package com.simplisell.tests.business;

import com.simplisell.persistence.AdPersistence;
import com.simplisell.persistence.hsqldb.PersistenceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.business.AccessAds;
import com.simplisell.tests.persistence.AdPersistenceStub;
import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AccessAdsTest
{
    private AccessAds accessAdsStub;
    private AccessAds accessAdsMock;

    private AdPersistence adPersistence;
    private AdPersistenceStub adStub;


    @Before
    public final void setup()
    {
        adStub = new AdPersistenceStub();
        accessAdsStub = new AccessAds(adStub);

        adPersistence = mock(AdPersistence.class);
        accessAdsMock = new AccessAds(adPersistence);
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

        Ad insertedAd = accessAdsStub.insertAd(uniqueAd);

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

        Ad ad = new Ad(adId, userName, adType, category, title, description, price, expiryDate, numReports);

        Ad insertedAd = accessAdsStub.insertAd(ad);
        // adding the same ad again
        insertedAd = accessAdsStub.insertAd(ad);

        assertNull(insertedAd);

        System.out.println("Finished AccessAdsTest: insert duplicate ad");
    }


    @Test
    public final void testInsertNull()
    {
        System.out.println("\nStarting AccessAdsTest: insert null");

        Ad insertedAd = accessAdsStub.insertAd(null);

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
        Ad insertedAd = accessAdsStub.insertAd(uniqueAd);
        Ad removedAd = accessAdsStub.removeAd(insertedAd);
        Ad foundAd = accessAdsStub.getAd(removedAd.getAdId());
        assertNull(foundAd);

        System.out.println("Finished AccessAdsTest: get deleted ad");
    }

    @Test (expected = PersistenceException.class)
    public void testRemoveAd()
    {
        System.out.println("\nStarting AccessAdsTest: remove ad");
        int adId = 1;
        Ad removedAd = accessAdsStub.getAd(adId);
        Assert.assertNotNull(removedAd);
        assertEquals(adId, removedAd.getAdId());

        removedAd = accessAdsStub.removeAd(removedAd);

        assertEquals(adId, removedAd.getAdId());
        Ad checkAd = accessAdsStub.getAd(1);        // Should throw an exception since you ad does not exist
        assertNull(checkAd);
        System.out.println("Finished AccessAdsTest: remove ad");
    }


    @Test
    public final void testRemoveNull()
    {
        System.out.println("\nStarting AccessAdsTest: remove null");

        Ad removeAd = accessAdsStub.removeAd(null);
        assertNull(removeAd);

        System.out.println("Finished AccessAdsTest: remove null");
    }


    @Test
    public void testGetAllAds()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads");

        List<Ad> ads = accessAdsStub.getAllAds();

        assertNotNull(ads);

        System.out.println("Finished AccessAdsTest: get all ads");
    }


    @Test
    public void testGetAllAdsByCategory()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads by category");

        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS, "test",
                "test", 1, null, 0));
        List<Ad> ads = accessAdsStub.getAllAdsByCategory(Category.ELECTRONICS);

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

        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS, "test",
                "test", 10, null, 0));
        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.OTHERS, "test", "test",
                100, null, 0));
        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS, "test",
                "test", 1000, null, 0));
        List<Ad> ads = accessAdsStub.sortPriceAsc(accessAdsStub.getAllAds());

        for (int i = 0; i < ads.size() - 1; i++)
        {
            double priceDiff = ads.get(i + 1).getPrice() - ads.get(i).getPrice();
            assertTrue(priceDiff >= 0);
        }

        System.out.println("Finished AccessAdsTest: sort price (ascending)");
    }


    @Test
    public void testSortPriceDesc()
    {
        System.out.println("\nStarting AccessAdsTest sort price (descending)");


        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS, "test",
                "test", 10, null, 0));
        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.OTHERS, "test", "test",
                100, null, 0));
        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS, "test",
                "test", 1000, null, 0));
        List<Ad> ads = accessAdsStub.sortPriceDesc(accessAdsStub.getAllAds());
        for (int i = 0; i < ads.size() - 1; i++)
        {
            double priceDiff = ads.get(i).getPrice() - ads.get(i + 1).getPrice();
            assertTrue(priceDiff >= 0);
        }

        System.out.println("Finished AccessAdsTest: sort price (descending)");
    }


    @Test
    public void testGetUserSpecificAds()
    {
        System.out.println("\nStarting AccessAdsTest: get all ads for specific user");

        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS, "test",
                "test", 1, null, 0));
        List<Ad> ads = accessAdsStub.getUserSpecificAds("test");

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

        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.ELECTRONICS, "test",
                "test", 10, null, 0));
        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.OFFERING, Category.OTHERS, "test", "test",
                100, null, 0));
        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.WANTED, Category.ELECTRONICS, "test", "test"
                , 2, null, 0));
        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.WANTED, Category.ELECTRONICS, "test", "test"
                , 0, null, 0));

        List<Ad> offeringAds = accessAdsStub.filterAdsByType(accessAdsStub.getAllAds(), AdType.OFFERING);
        List<Ad> wantedAds = accessAdsStub.filterAdsByType(accessAdsStub.getAllAds(), AdType.WANTED);

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

        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.WANTED, Category.ELECTRONICS, "test", "test"
                , 0, null, 0));
        accessAdsStub.insertAd(new Ad(adStub.getNewAdId(), "test", AdType.WANTED, Category.ELECTRONICS, "test", "test"
                , 0, null, 0));

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -30);

        Date oldDate = new Date(c.getTimeInMillis());
        //Set 3 ads to expire
        accessAdsStub.getAd(0).setExpiryDate(oldDate);
        accessAdsStub.getAd(1).setExpiryDate(oldDate);
        accessAdsStub.getAd(2).setExpiryDate(oldDate);

        int sizeBefore = accessAdsStub.getAllAds().size();
        accessAdsStub.removeExpiredAds();
        int sizeAfter = accessAdsStub.getAllAds().size();

        assertTrue(sizeBefore == sizeAfter + 3);

        System.out.println("Finished AccessAdsTest: remove expired ads");
    }


    @Test
    public void testRepostAd()
    {
        System.out.println("\nStarting AccessAdsTest: repost Ad");

        Ad repostThisAd = accessAdsStub.getAd(0);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -30);
        Date oldDate = new Date(c.getTimeInMillis());
        repostThisAd.setExpiryDate(oldDate);

        //New Expiry Date
        int daysTillExpired = 21;
        Calendar c1 = Calendar.getInstance();
        c1.add(Calendar.DATE, daysTillExpired);
        Date newDate = new Date(c1.getTimeInMillis());

        accessAdsStub.repostAd(repostThisAd.getAdId());

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
        Ad updateThisAd = accessAdsStub.getAd(0);
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

        accessAdsStub.updateAd(updatedAd);

        assertNotEquals(oldTile, updateThisAd.getTitle());
        assertNotEquals(oldPrice, updateThisAd.getPrice());
        assertEquals(oldDescription, updateThisAd.getDescription());
        assertEquals(oldCategory, updateThisAd.getCategory());
        assertEquals(oldNumReports, updateThisAd.getNumReports());

        assertEquals(newTitle, updateThisAd.getTitle());
        assertEquals(newPrice, updateThisAd.getPrice(), 0.001);

        System.out.println("\nFinished AccessAdsTest: update Ad (category and description does not change)");
    }


    @Test
    public final void testReportAd()
    {
        System.out.println("\nStarting AccessAdsTest: report Ad");

        final int TEST_AD_ID = 0;
        final int TIMES_TO_BE_REPORTED = 2;
        int adCounter = 0;

        // create a new ad (to be reported)
        when(adPersistence.getNewAdId()).thenReturn(TEST_AD_ID);
        final Ad adToBeReported = new Ad(accessAdsMock.getNewAdId(), "testOwner", AdType.WANTED, Category.OTHERS,
                "testTitle", "testDescription", 45, null, 0);

        // insert Ad
        when(adPersistence.insertAd(adToBeReported)).thenReturn(adToBeReported);
        accessAdsMock.insertAd(adToBeReported);

        // retrieve the ad
        when(adPersistence.getAd(TEST_AD_ID)).thenReturn(adToBeReported);

        doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable
            {
                Object[] args = invocation.getArguments();
                Ad adToBeReported = accessAdsMock.getAd((int) args[0]);
                adToBeReported.reportAd();

                return null;
            }
        }).when(adPersistence).reportAd(TEST_AD_ID);


        while (adCounter != TIMES_TO_BE_REPORTED)
        {
            accessAdsMock.reportAd(adToBeReported.getAdId());
            adCounter++;
        }

        final Ad reportedAd = accessAdsMock.getAd(TEST_AD_ID);

        assertEquals("Number of reports not as expected", TIMES_TO_BE_REPORTED, reportedAd.getNumReports());

        verify(adPersistence).getNewAdId();
        verify(adPersistence).insertAd(adToBeReported);
        verify(adPersistence, times(TIMES_TO_BE_REPORTED)).reportAd(TEST_AD_ID);
        // getAd() is called everytime the ad is reported (TIMES_TO_BE_REPORTED) + 1 (when configuring return
        // behaviour for the reportAd function)
        verify(adPersistence, times(TIMES_TO_BE_REPORTED + 1)).getAd(TEST_AD_ID);

        System.out.println("\nFinished AccessAdsTest: report Ad");
    }


    @Test
    public final void testGetReportedAds()
    {
        System.out.println("\nStarting AccessAdsTest: get reported Ads");

       final Ad newAd1 = new Ad(0, "testOwner1", AdType.WANTED, Category.OTHERS, "testTitle1", "testDescription1",
                45, null, 0);

        final Ad newAd2 = new Ad(1, "testOwner2", AdType.WANTED, Category.OTHERS, "testTitle2", "testDescription2",
                45, null, 0);

        final Ad newAd3 = new Ad(2, "testOwner3", AdType.WANTED, Category.OTHERS, "testTitle3", "testDescription3",
                45, null, 0);

        // insert Ad
        when(adPersistence.insertAd(any(Ad.class))).thenAnswer(new Answer<Ad>()
        {
            @Override
            public Ad answer(InvocationOnMock invocation) throws Throwable
            {
                Object[] args = invocation.getArguments();
                return (Ad) args[0];
            }
        });

        accessAdsMock.insertAd(newAd1);
        accessAdsMock.insertAd(newAd2);
        accessAdsMock.insertAd(newAd3);

        // retrieve the ad
        when(adPersistence.getAd(1)).thenReturn(newAd1);
        when(adPersistence.getAd(2)).thenReturn(newAd1);

        // report ad
        doAnswer(new Answer()
        {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable
            {
                Object[] args = invocation.getArguments();
                Ad adToBeReported = accessAdsMock.getAd((int) args[0]);
                adToBeReported.reportAd();

                return null;
            }
        }).when(adPersistence).reportAd(any(Integer.class));

        // report 2 out of the 3 ads
        accessAdsMock.reportAd(newAd2.getAdId());
        accessAdsMock.reportAd(newAd3.getAdId());

        final List<Ad> reportedAdsList = new ArrayList<>();
        reportedAdsList.add(newAd2);
        reportedAdsList.add(newAd3);

        when(adPersistence.getreportedAds()).thenReturn(reportedAdsList);


        assertEquals("Reported Ads List is not the same as expected", reportedAdsList, accessAdsMock.getReportedAds());


        // the total no of ads are three
        verify(adPersistence, times(3)).insertAd(any(Ad.class));

        verify(adPersistence).getAd(1);
        verify(adPersistence).getAd(2);

        // 2 (out of 3) ads are reported
        verify(adPersistence, times(2)).reportAd(any(Integer.class));

        verify(adPersistence).getreportedAds();

        System.out.println("\nFinished AccessAdsTest: get reported Ads");
    }


    @After
    public final void tearDown()
    {
        adStub = null;
        accessAdsStub = null;

        adPersistence = null;
        accessAdsMock = null;
    }
}
