package com.simplisell.tests.business;

import com.simplisell.business.Search;
import com.simplisell.objects.Ad;
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

        System.out.println("\nEnding test testGetAllAds");
    }
}
