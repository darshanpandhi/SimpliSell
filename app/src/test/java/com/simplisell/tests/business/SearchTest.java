package com.simplisell.tests.business;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.business.Search;
import com.simplisell.business.AccessAds;

public class SearchTest
{

    private Search search;
    private AccessAds adPersistence;

    @Before
    public final void setup()
    {
        search = new Search();
        adPersistence = new AccessAds();
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
    public void testGetReportedAdsMoreThan2Reports()
    {
        System.out.println("\nStarting testSearch: get all reported ads (more than 2 reports)");

        Ad reportedAd = adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.BOOKS,
                "reportedAd3Reports", "reportedAd3Reports", 1));
        reportedAd.incrementNumberOfReports();
        reportedAd.incrementNumberOfReports();

        reportedAd = adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.BOOKS,
                "reportedAd4Reports", "reportedAd4Reports", 1));        List<Ad> ads = search.getReportedAds();
        reportedAd.incrementNumberOfReports();
        reportedAd.incrementNumberOfReports();
        reportedAd.incrementNumberOfReports();
        reportedAd.incrementNumberOfReports();

        int minNumberReports = 2;

        for (Ad ad : ads)
        {
            assertTrue(ad.getNumberOfReports() >= minNumberReports);
        }

        System.out.println("Finished testSearch: get all reported ads (more than 2 reports)");
    }

    @Test
    public void testGetReportedAdsLessThan2Reports()
    {
        System.out.println("\nStarting testSearch: get all reported ads (less than 2 reports)");

        List<Ad> ads = search.getReportedAds();

        int minNumberReports = 2;

        for (Ad ad : ads)
        {
            assertFalse(ad.getNumberOfReports() < minNumberReports);
        }

        System.out.println("Finished testSearch: get all reported ads (less than 2 reports)");
    }
}
