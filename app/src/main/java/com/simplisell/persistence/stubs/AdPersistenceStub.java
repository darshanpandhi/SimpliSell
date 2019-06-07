package com.simplisell.persistence.stubs;

import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.objects.UserAdvertiser;
import com.simplisell.persistence.AdPersistence;
import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import java.util.ArrayList;

final class AdPersistenceStub implements AdPersistence
{
    private ArrayList<Ad> allAdList;

    private AdPersistenceStub()
    {
        allAdList = new ArrayList<>();

        final UserAdvertiser bob = new UserAdvertiser("Bob", "1234");
        final UserAdvertiser allice = new UserAdvertiser("Allice", "4321");
        final UserAdvertiser jay = new UserAdvertiser("Jay", "abc");

        allAdList.add(new Ad(bob, AdType.OFFERING, Category.ELECTRONICS, ));

    }

    public void insertAd(final Ad ad)
    {
        UserAdvertiser adOwner = ad.getAdOwner();

        adOwner.

    }
}
