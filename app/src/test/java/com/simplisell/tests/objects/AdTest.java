package com.simplisell.tests.objects;

import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;


public class AdTest
{
    @Test
    public void testNewAd()
    {
        Ad newAd;

        System.out.println("\nStarting testAd");

        newAd = new Ad("AdOwner1", AdType.OFFERING, Category.ACCOMMODATION, "Roommate required",
                "Roommate Ad Description", 350);

        assertNotNull(newAd);

        assertEquals(1, newAd.getAdId());
        assertEquals("AdOwner1", newAd.getAdOwner());
        assertEquals(AdType.OFFERING,newAd.getAdType());
        assertEquals(Category.ACCOMMODATION, newAd.getCategory());
        assertEquals("Roommate required", newAd.getTitle());
        assertEquals("Roommate Ad Description", newAd.getDescription());
        assertEquals(350, newAd.getPrice(), 0);

        System.out.println("Finished testAd");
    }
}