package com.simplisell.persistence.stubs;

import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.objects.UserAdvertiser;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;
import java.util.ArrayList;

final class AdPersistenceStub implements AdPersistence
{
    private ArrayList<Ad> allAdList;

    private AdPersistenceStub()
    {
        allAdList = new ArrayList<>();

        final UserAdvertiser bob = new UserAdvertiser("Bob", "1234");
        final UserAdvertiser allice = new UserAdvertiser("Allice", "4321");
        final UserAdvertiser jay = new UserAdvertiser("Jay", "abc");

        final AdType adType = AdType.OFFERING;
        final Category category = Category.ELECTRONICS;
        final String title = "Brand new 6th Generation 128 gb Apple iPad 9.7 inch";
        final String description = "Selling my iPad as I got a new 2 in 1 laptop. Hardly Used" +
                "(15 days). Got the original packaging (boxes) and all accessories. All the items" +
                " are still in their warranty period. Have added a picture of the warranty with" +
                " this ad.\n" + "\n" + "I have attached a premium quality tampered glass on the " +
                "iPad to protect the screen. I also have a blue denim cover which can hold the " +
                "apple pencil as well.";
        final double price = 554.99;

        Ad newAd = new Ad(bob, adType, category, title, description, price);

        allAdList.add(newAd);
        bob.addNewAd(newAd);

    }

    public void insertAd(final Ad ad)
    {
        UserAdvertiser adOwner = ad.getAdOwner();

        adOwner.

    }
}
