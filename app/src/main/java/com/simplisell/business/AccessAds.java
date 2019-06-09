package com.simplisell.business;


import com.simplisell.application.Services;
import com.simplisell.persistence.AdPersistence;

final class AccessAds
{
    private final AdPersistence adPersistence;


    private AccessAds()
    {
        adPersistence = Services.getAdPersistence();
    }


}