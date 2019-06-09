package com.simplisell.persistence.stubs;

import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;

import com.simplisell.objects.Ad;
import com.simplisell.objects.OfferingAd;
import com.simplisell.objects.WantedAd;

import java.util.ArrayList;
import java.util.List;


public class AdPersistenceStub implements AdPersistence
{
    private List<Ad> ads;

    public AdPersistenceStub()
    {
        ads = new ArrayList<>();

        String adOwner = "bob";
        Category category = Category.ELECTRONICS;
        String title = "iPad 6th Gen";
        String description = "iPad Ad description";
        double price = 554.99;

        Ad newAd = new OfferingAd(adOwner, category, title, description, price);
        ads.add(newAd);

        adOwner = "Allice";
        category = Category.JOBS_SERVICES;
        title = "Wanted Tutoring Services";
        description = "Tutoring Services Description";

        newAd = new WantedAd(adOwner, category, title, description);
        ads.add(newAd);

        adOwner = "Jay";
        category = Category.TRANSPORTATION;
        title = "CarPooling Ad Title";
        description = "CarPooling Ad Description";
        price = 100;

        newAd = new OfferingAd(adOwner, category, title, description, price);
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
