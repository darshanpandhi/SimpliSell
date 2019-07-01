package com.simplisell.persistence;

import com.simplisell.objects.Ad;

import java.util.List;

public interface AdPersistence
{
    List<Ad> getAds();

    Ad getAd(int adID);

    Ad insertAd(final Ad ad);

    Ad removeAd(final Ad ad);

    void reportAd(int adID);
}
