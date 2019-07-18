package com.simplisell.persistence;

import com.simplisell.objects.Ad;

import java.sql.Date;
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

    void changeExpiryDate(final int adId, Date newDate);

    List<Ad> getReportedAds();

    int getNewAdId();
}
