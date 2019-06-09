package com.simplisell.business;


import com.simplisell.application.Services;
import com.simplisell.persistence.AdPersistence;
<<<<<<< HEAD
import com.simplisell.objects.Ad;
import java.util.List;
=======
>>>>>>> f37bc817527325b581c35d265a4210ec96758987

final class AccessAds
{
    private final AdPersistence adPersistence;


    private AccessAds()
    {
        adPersistence = Services.getAdPersistence();
    }

<<<<<<< HEAD
    public final List<Ad> getAllAds()
    {
        return adPersistence.getAds();
    }

=======
>>>>>>> f37bc817527325b581c35d265a4210ec96758987

}