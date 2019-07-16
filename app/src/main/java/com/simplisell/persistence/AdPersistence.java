package com.simplisell.persistence;

import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;

import java.util.List;

public interface AdPersistence
{
    List<Ad> getAds();

    Ad getAd(int adID);

    Ad insertAd(final Ad ad);

    Ad removeAd(final Ad ad);

    void repostAd(int adID);

    void updateAd(final Ad ad);

    void reportAd(final int adId);

    List<Ad> getReportedAds();

    int getNewAdId();

    //mockito
    List<Ad> getAdsByType(AdType ad);

    //mockito
    List<Ad> getAdsByCategory(Category category);
}
