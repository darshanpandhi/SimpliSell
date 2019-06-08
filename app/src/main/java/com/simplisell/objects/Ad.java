package com.simplisell.objects;

import java.util.Date;

public final class Ad
{
    private static int idCounter = 1;

    private final int adId;
    private final String adOwner;
    private final AdType adType;
    private final Category category;
    private final String title;
    private final String description;
    private final double price;


    Ad()
    {
        adId = 0;
        adOwner = null;
        adType = null;
        category = null;
        title = null;
        description = null;
        price = -1;
    }


    public Ad(final String adOwner, final AdType adType, final Category category, final
    String title, final String description, final double price)
    {
        adId = idCounter;
        idCounter++;

        this.adOwner = adOwner;
        this.adType = adType;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
    }


    // Some ads especially of type Wanted wouldn't have price associated with it
    public Ad(final String adOwner, final AdType adType, final Category category, final
    String title, final String description)
    {
        adId = idCounter;
        idCounter++;

        this.adOwner = adOwner;
        this.adType = adType;
        this.category = category;
        this.title = title;
        this.description = description;

        price = -1;
    }


    public final String getAdOwner()
    {
       return adOwner;
    }
}

