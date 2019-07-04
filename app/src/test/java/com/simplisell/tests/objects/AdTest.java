package com.simplisell.tests.objects;

import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

        newAd = new Ad("AdOwner1", null, null, null,
                null, 0);
        newAd.setCategory(Category.ACCOMMODATION);
        newAd.setTitle("Roommate required");
        newAd.setDescription("Roommate Ad Description");
        newAd.setPrice(350);

        assertNotNull(newAd);

        assertEquals("AdOwner1", newAd.getAdOwner());
        assertEquals(Category.ACCOMMODATION, newAd.getCategory());
        assertEquals("Roommate required", newAd.getTitle());
        assertEquals("Roommate Ad Description", newAd.getDescription());
        assertEquals(350, newAd.getPrice(), 0);

        System.out.println("Finished testAd");
    }
}