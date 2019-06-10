package com.simplisell.business;


import com.simplisell.application.Services;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;
import java.util.List;

public final class AccessAds
{
    private final AdPersistence adPersistence;


    public AccessAds()
    {
        adPersistence = Services.getAdPersistence();
    }


    public final Ad getAd(int adId)
    {
        return adPersistence.getAd(adId);
    }

    public final List<Ad> getAllAds()
    {
        return adPersistence.getAds();
    }

    public final void insertAd(final Ad adToBeInserted)
    {
        adPersistence.insertAd(adToBeInserted);
    }

    public final void removeAd(final Ad adToBeRemoved)
    {
        adPersistence.removeAd(adToBeRemoved);
    }
}