package com.simplisell.objects;

import java.util.Date;

public final class Ad
{
    private static int idCounter = 1;

    private final int adId;
    private final UserAdvertiser adOwner;
    private final AdType adType;
    private final Category category;
    private final Date adPostDate;
    private final String title;
    private final String description;
    private final double price;


    Ad()
    {
        adId = 0;
        adOwner = null;
        adType = null;
        category = null;
        adPostDate = null;
        title = null;
        description = null;
        price = 0;
    }


    Ad(final UserAdvertiser adOwner, final AdType adType, final Category category, final Date
            adPostDate, final String title, final String description, final double price)
    {
        adId = idCounter;
        idCounter++;

        this.adOwner = adOwner;
        this.adType = adType;
        this.category = category;
        this.adPostDate = adPostDate;
        this.title = title;
        this.description = description;
        this.price = price;
    }


    public final UserAdvertiser getAdOwner()
    {
       return adOwner;
    }
}

