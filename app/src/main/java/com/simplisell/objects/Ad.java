package com.simplisell.objects;

public final class Ad
{
    private static int idCounter = 1;

    private final int adId;
    private final String adOwner;
    private AdType adType;
    private Category category;
    private String title;
    private String description;
    private double price;
    private int numReports;


    public Ad(int adId, final String adOwner, final AdType adType, final Category category, final
    String title, final String description, final double price)
    {
        this.adId = adId;
        this.adOwner = adOwner;
        this.adType = adType;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        numReports = 0;
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


    //Getters and Setters below


    public int getAdId()
    {
        return adId;
    }


    public String getAdOwner()
    {
        return adOwner;
    }


    public AdType getAdType()
    {
        return adType;
    }


    public void setAdType(AdType adType)
    {
        this.adType = adType;
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


    public double getPrice()
    {
        return price;
    }


    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getNumReports()
    {
        return numReports;
    }

    public void incrementNumReports()
    {
        numReports++;
    }
}

