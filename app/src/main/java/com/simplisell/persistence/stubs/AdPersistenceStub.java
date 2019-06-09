package com.simplisell.persistence.stubs;

import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;
import java.util.ArrayList;
import java.util.List;

public class AdPersistenceStub implements AdPersistence
{
    private List<Ad> ads;

    public AdPersistenceStub()
    {
        ads = new ArrayList<>();

        String adOwner = "bob";
        AdType adType = AdType.OFFERING;
        Category category = Category.ELECTRONICS;
        String title = "iPad 6th Gen";
        String description = "iPad Ad description";
        double price = 554.99;

        Ad newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);

        adOwner = "Allice";
        adType = AdType.WANT;
        category = Category.JOBS_SERVICES;
        title = "Wanted Tutoring Services";
        description = "Tutoring Services Description";
        price = 40;

        newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);

        adOwner = "Jay";
        adType = AdType.OFFERING;
        category = Category.TRANSPORTATION;
        title = "CarPooling Ad Title";
        description = "CarPooling Ad Description";
        price = 100;

        newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);
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
