package com.simplisell.objects;

import java.util.Date;

abstract class Ad
{
    private final User adOwner;
    private final String title;
    private final String description;
    private final Date adPostDate;


    Ad()
    {
        adOwner = null;
        title = null;
        description = null;
        adPostDate = null;
    }


    private Ad(User adOwner, String title, String description, Date adPostDate)
    {
        this.adOwner = adOwner;
        this.title = title;
        this.description = description;
        this.adPostDate = adPostDate;
    }
}

