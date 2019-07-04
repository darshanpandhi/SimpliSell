package com.simplisell.objects;


import java.sql.Date;
import java.util.Calendar;

public final class Ad
{
    private int adId;
    private String adOwner;
    private AdType adType;
    private Category category;
    private String title;
    private String description;
    private double price;

    private Date expiryDate;

    public Ad(int adId, final String adOwner, final AdType adType, final Category category, final
    String title, final String description, final double price, final Date expiryDate)
    {
        this.adId = adId;
        this.adOwner = adOwner;
        this.adType = adType;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    //Constructor for AccessAdsTest
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
        this.expiryDate = calculateExpiryDate();
    }

    //Constructor for AccessAdsTest
    public Ad(final String adOwner, final AdType adType, final Category category, final
    String title, final String description, final double price)
    {
        this.adId = -1;
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
    public Category getCategory()
    {
        return category;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDescription()
    {
        return description;
    }
    public double getPrice()
    {
        return price;
    }
    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setAdId(int newId)
    {
        this.adId = newId;
    }
    public void setCategory(Category category)
    {
        this.category = category;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setExpiryDate(Date date)
    {
        this.expiryDate = date;
    }

    public Date calculateExpiryDate()
    {
        int daysTillExpired = 21;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, daysTillExpired);

        return new Date(c.getTimeInMillis());
    }
    public void resetExpiryDate()
    {
        expiryDate = calculateExpiryDate();
    }
}