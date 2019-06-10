package com.simplisell.business;


import com.simplisell.application.Services;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;
import java.util.List;

final class AccessAds
{
    private final AdPersistence adPersistence;


    private AccessAds()
    {
        adPersistence = Services.getAdPersistence();
    }


    public final List<Ad> getAllAds()
    {
        return adPersistence.getAds();
    }

}