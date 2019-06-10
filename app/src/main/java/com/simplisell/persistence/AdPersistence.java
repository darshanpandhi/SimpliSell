package com.simplisell.persistence;

import com.simplisell.objects.Ad;
import java.util.List;

public interface AdPersistence
{
    List<Ad> getAds();
    Ad getAd(int adId);
    void insertAd(final Ad ad);
    void removeAd(final Ad ad);
}
