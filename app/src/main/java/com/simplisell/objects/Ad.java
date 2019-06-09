package com.simplisell.objects;

import java.util.Date;

public abstract class Ad
{
    private static int idCounter = 1;

    private final int adId;
    private final String adOwner;
    private Category category;
    private String title;
    private String description;


    Ad(final String adOwner, final Category category, final String title,
              final String description)
    {
        adId = idCounter;
        idCounter++;

        this.adOwner = adOwner;
        this.category = category;
        this.title = title;
        this.description = description;
    }


    //Getters and Setters below

    public int getAdId()
    {
        return adId;
    }

    public String getAdOwner()
    {
        return adOwner;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}

