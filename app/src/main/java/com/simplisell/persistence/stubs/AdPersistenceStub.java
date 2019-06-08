package com.simplisell.persistence.stubs;

import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;
import java.util.ArrayList;
import java.util.List;

final class AdPersistenceStub implements AdPersistence
{
    private List<Ad> ads;

    private AdPersistenceStub()
    {
        ads = new ArrayList<>();

        String adOwner = "bob";
        AdType adType = AdType.OFFERING;
        Category category = Category.ELECTRONICS;
        String title = "Brand new 6th Generation 128 gb Apple iPad 9.7 inch";
        String description = "Selling my iPad as I got a new 2 in 1 laptop. Hardly Used" +
                "(15 days). Got the original packaging (boxes) and all accessories. All the items" +
                " are still in their warranty period. Have added a picture of the warranty with" +
                " this ad.\n" + "\n" + "I have attached a premium quality tampered glass on the " +
                "iPad to protect the screen. I also have a blue denim cover which can hold the " +
                "apple pencil as well.";
        double price = 554.99;

        Ad newAd = new Ad(adOwner, adType, category, title, description, price);

        ads.add(newAd);

        ads.add(new Ad("bob", AdType.OFFERING, Category.ELECTRONICS, "Electronics Test 1",
                "Description for electronics test 1", 10));
        ads.add(new Ad("bob", AdType.OFFERING, Category.ELECTRONICS, "Electronics Test 2",
                "Description for electronics test 2", 15));
        ads.add(new Ad("bob", AdType.OFFERING, Category.ELECTRONICS, "Electronics Test 3",
                "Description for electronics test 3", 20));

    }

    public List<Ad> getAds()
    {
        return ads;
    }

    public void insertAd(final Ad ad)
    {
        //UserAdvertiser adOwner = ad.getAdOwner();

        //adOwner.

    }

    public void editAd(final Ad ad)
    {

    }

    public void removeAd(final Ad ad)
    {

    }
}
