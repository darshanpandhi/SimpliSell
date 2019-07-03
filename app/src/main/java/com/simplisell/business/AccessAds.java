package com.simplisell.business;

import com.simplisell.application.Services;
import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public final class AccessAds
{
    private final AdPersistence adPersistence;
    private final List<Ad> allAds;

    public AccessAds()
    {
        adPersistence = Services.getAdPersistence();
        allAds = adPersistence.getAds();
    }

    public AccessAds(final AdPersistence adPersistence)
    {
        this.adPersistence = adPersistence;
        allAds = adPersistence.getAds();
    }

    public final Ad insertAd(final Ad newAd)
    {
        if (newAd == null || newAd.getClass() != Ad.class)
        {
            return null;
        }

        int newAdId = 0;
        List<Ad> allAds = getAllAds();

        if (allAds.size() != 0)
        {
            Ad maxAd = Collections.max(allAds, compareAdId);
            newAdId = maxAd.getAdId() + 1;
        }

        newAd.setAdId(newAdId);
        return adPersistence.insertAd(newAd);
    }

    public final Ad getAd(int adId)
    {
        return adPersistence.getAd(adId);
    }

    public final List<Ad> getAllAds()
    {
        return adPersistence.getAds();
    }

    public final Ad removeAd(final Ad adToBeRemoved)
    {
        return adPersistence.removeAd(adToBeRemoved);
    }

    public final void reportAd(final int adID)
    {
        adPersistence.reportAd(adID);
    }

    public void repostAd(final int adID)
    {
        adPersistence.repostAd(adID);
    }

    public final int getAdID()
    {
        return adPersistence.getAdID();
    }

    public final void updateAd(Ad ad)
    {
        adPersistence.updateAd(ad);
    }

    public List<Ad> getAllAdsByCategory(Category category)
    {
        List<Ad> adList = new ArrayList<Ad>();

        for (Ad ad : allAds)
        {
            if (ad.getCategory() == category)
            {
                adList.add(ad);
            }
        }

        return adList;
    }

    public List<Ad> getUserSpecificAds(String userName)
    {
        List<Ad> adList = new ArrayList<Ad>();

        for (Ad ad : allAds)
        {
            if (ad.getAdOwner().equals(userName))
            {
                adList.add(ad);
            }
        }

        return adList;
    }

    public List<Ad> getReportedAds()
    {
        List<Ad> adList = new ArrayList<Ad>();

        int minNumberOfReports = 3;

        for (Ad ad: allAds)
        {
            if (ad.getNumReports() >= minNumberOfReports)
            {
                adList.add(ad);
            }
        }

        return adList;
    }

    public List<Ad> sortPriceAsc(List<Ad> ads)
    {
        Collections.sort(ads, compareByPriceAsc);

        return ads;
    }


    public List<Ad> sortPriceDesc(List<Ad> ads)
    {
        Collections.sort(ads, compareByPriceDesc);

        return ads;
    }

    public List<Ad> filterAdsByType(List<Ad> ads, AdType adType)
    {
        List<Ad> adList = new ArrayList<Ad>();

        for (Ad ad : ads)
        {
            if (ad.getAdType() == adType)
            {
                adList.add(ad);
            }
        }

        return adList;
    }

    private List<Ad> removeExpiredAds(List<Ad> ads)
    {
        long currTime = System.currentTimeMillis();

        Date currDate = new Date(currTime);

        for (Ad ad : ads)
        {
            Date expiryDate = ad.getExpireDate();
            if (expiryDate.compareTo(currDate) < 0)
            {
                ads.remove(ad);
                adPersistence.removeAd(ad);
            }
        }

        return ads;
    }

    //Need multiple comparator functions per asc or desc sort due to API level 23
    Comparator<Ad> compareByPriceDesc = new Comparator<Ad>()
    {
        @Override
        public int compare(Ad o1, Ad o2)
        {
            return o1.getPrice() > o2.getPrice() ? -1 : o1.getPrice() < o2.getPrice() ? 1 : 0;
        }
    };

    Comparator<Ad> compareByPriceAsc = new Comparator<Ad>()
    {
        @Override
        public int compare(Ad o1, Ad o2)
        {
            return o1.getPrice() > o2.getPrice() ? 1 : o1.getPrice() < o2.getPrice() ? -1 : 0;
        }
    };

    Comparator<Ad> compareAdId = new Comparator<Ad>()
    {
        @Override
        public int compare (Ad o1, Ad o2)
        {
            return o1.getAdId() > o2.getAdId() ? 1 : o1.getAdId() < o2.getAdId() ? -1 : 0;
        }
    };
}
