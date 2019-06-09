package com.simplisell.objects;

public final class OfferingAd extends Ad
{
    private double price;


    public OfferingAd(final String adOwner, final Category category, final String title,
                       final String description, final double price)
    {
        super(adOwner, category, title, description);

        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}