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
    private int numReports;

    private Date expiryDate;

    public Ad(int adId, final String adOwner, final AdType adType, final Category category, final
    String title, final String description, final double price, final int numReports, final Date expiryDate)
    {
        this.adId = adId;
        this.adOwner = adOwner;
        this.adType = adType;
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.numReports = numReports;
        this.expiryDate = expiryDate;
    }

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
        this.numReports = 0;
    }


    //Getters and Setters below


    public int getAdId()
    {
        return adId;
    }

    public void setAdId(int newId)
    {
        this.adId = newId;
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

    public Date getExpireDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(Date date)
    {
        this.expiryDate = date;
    }

    public void incrementNumReports()
    {
        numReports++;
    }

    public Date calculateExpireDate()
    {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 21);

        return new Date(c.getTimeInMillis());
    }

    public void resetExpiryDate()
    {
        expiryDate = calculateExpireDate();
    }
}