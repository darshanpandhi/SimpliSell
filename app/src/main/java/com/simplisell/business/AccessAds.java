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

    public AccessAds(final AdPersistence adPersistence) {
        this.adPersistence = adPersistence;
    }

    public final Ad insertAd(final Ad newAd)
    {
        return adPersistence.insertAd(newAd);
    }


    public final Ad getAd(int adId)
    {
        return adPersistence.getAd(adId);
    }


    public final List<Ad> getAllAds()
    {
        return adPersistence.getAds();
    }


    public final Ad removeAd(final Ad adToBeRemoved)
    {
        return adPersistence.removeAd(adToBeRemoved);
    }

    public final void reportAd(final int adID)
    {
        adPersistence.reportAd(adID);
    }

    public void repostAd(final int adID)
    {
        adPersistence.repostAd(adID);
    }

    public final int getAdID()
    {
        return adPersistence.getAdID();
    }

    public final void updateAd(Ad ad)
    {
        adPersistence.updateAd(ad);
    }
}