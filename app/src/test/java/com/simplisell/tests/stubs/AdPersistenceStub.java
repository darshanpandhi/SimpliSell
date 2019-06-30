package com.simplisell.tests.stubs;

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

        String adOwner = "Bob";
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


        adOwner = "Bob";
        adType = AdType.OFFERING;
        category = Category.BOOKS;
        title = "Transcendence- A spiritual journey with pramukh swami maharaj";
        description = "A nice book by Dr APJ abdul kalaam";
        price = 0;

        newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);


        adOwner = "Allice";
        adType = AdType.OFFERING;
        category = Category.BOOKS;
        title = "Analysis of Algorithms";
        description = "Book for COMP2080 and COMP3170";
        price = 20;

        newAd = new Ad(adOwner, adType, category, title, description, price);
        ads.add(newAd);
    }


    public List<Ad> getAds()
    {
        return ads;
    }


    public final Ad getAd(int adId)
    {
        Ad currentAd;
        Ad requiredAd = null;
        boolean adFound = false;
        int currentIndex = 0;

        while (!adFound && currentIndex < ads.size())
        {
            currentAd = ads.get(currentIndex);

            if (currentAd.getAdId() == adId)
            {
                requiredAd = currentAd;
                adFound = true;
            }

            currentIndex++;
        }

        return requiredAd;
    }


    public final Ad insertAd(final Ad newAd)
    {
        Ad insertedAd = null;

        if (newAd != null)
        {
            final int newAdId = newAd.getAdId();

            // verify the presence of an ad with the same ad id
            if (getAd(newAdId) == null)
            {
                ads.add(newAd);
                insertedAd = newAd;
            }
        }

        return insertedAd;
    }


    public final Ad removeAd(final Ad adToBeRemoved)
    {
        Ad removedAd = null;
        final int index = ads.indexOf(adToBeRemoved);

        if (index >= 0)
        {
            ads.remove(adToBeRemoved);
            removedAd = adToBeRemoved;
        }

        return removedAd;
    }
}
