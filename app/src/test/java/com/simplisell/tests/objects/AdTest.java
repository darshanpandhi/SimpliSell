package com.simplisell.tests.objects;

import com.simplisell.business.AccessAds;
import com.simplisell.tests.persistence.AdPersistenceStub;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;

public class AdTest
{
    private AdPersistenceStub adStub;

    @Before
    public final void setup()
    {
        adStub = new AdPersistenceStub();
    }

    @Test
    public void testNewAd()
    {
        Ad newAd;

        System.out.println("\nStarting testAd");

        newAd = new Ad(adStub.getNewAdId(), "AdOwner1", null, null, null,
                null, 0, null, 0);
        newAd.setCategory(Category.ACCOMMODATION);
        newAd.setTitle("Roommate required");
        newAd.setDescription("Roommate Ad Description");
        newAd.setPrice(350);
        newAd.reportAd();

        assertNotNull(newAd);

        assertEquals("AdOwner1", newAd.getAdOwner());
        assertEquals(Category.ACCOMMODATION, newAd.getCategory());
        assertEquals("Roommate required", newAd.getTitle());
        assertEquals("Roommate Ad Description", newAd.getDescription());
        assertEquals(350, newAd.getPrice(), 0);
        assertEquals(1, newAd.getNumReports());

        System.out.println("Finished testAd");
    }

    @After
    public final void teardown()
    {
        adStub = null;
    }
}