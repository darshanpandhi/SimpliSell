package com.simplisell.persistence.stubs;

import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;
import com.simplisell.objects.User;

final class AdPersistenceStub implements AdPersistence
{
    public void insertAd(final Ad ad)
    {
        User adOwner = ad.getAdOwner();

        adOwner.

    }
}
