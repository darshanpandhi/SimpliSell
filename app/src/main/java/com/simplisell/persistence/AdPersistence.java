package com.simplisell.persistence;

import com.simplisell.objects.Ad;

public interface AdPersistence
{
    void insertAd(final Ad ad);
    void editAd(final Ad ad);
    void removeAd(final Ad ad);
}
