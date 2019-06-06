package com.simplisell.objects;

import java.util.Date;

final class Ad
{
    private static int idCounter = 1;

    private final int adId;
    private final User adOwner;
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


    private Ad(User adOwner, AdType adType, Category category, Date adPostDate, String title, String
            description, double price)
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
}

