package com.simplisell.tests.business;

import com.simplisell.business.AccessAds;
import com.simplisell.business.Search;
import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

public class SearchTest
{

    @Test
    public void testGetAllAds()
    {
        System.out.println("\nStarting test testGetAllAds");

        Search search = new Search();
        List<Ad> ads = search.getAllAds();
        assertNotNull(ads);

        System.out.println("Ending test testGetAllAds");
    }

    @Test
    public void testGetAllAdsByCategory()
    {
        System.out.println("\nStarting test testGetAllAdsByCategory");

        Search search = new Search();
        AccessAds adPersistence = new AccessAds();
        adPersistence.insertAd(new Ad("test", AdType.OFFERING, Category.ELECTRONICS,
                "test", "test", 1));
        List<Ad> ads = search.getAllAdsByCategory(Category.ELECTRONICS);
        for (Ad ad : ads)
        {
            assertEquals(ad.getCategory(), Category.ELECTRONICS);

        }

        System.out.println("Ending test testGetAllAdsByCategory");
    }

    @Test
    public void testSortPriceAsc()
    {
        System.out.println("\nStarting test testSortPriceAsc");

        AccessAds adPersistence = new AccessAds();
        Search search = new Search();
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

        System.out.println("Ending test testSortPriceAsc");
    }

    @Test
    public void testSortPriceDesc()
    {
        System.out.println("\nStarting test testSortPriceDesc");

        AccessAds adPersistence = new AccessAds();
        Search search = new Search();
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

        System.out.println("Ending test testSortPriceDesc");
    }
}
