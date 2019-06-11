package com.simplisell.business;

import com.simplisell.application.Services;
import com.simplisell.objects.Ad;
import com.simplisell.objects.Category;
import com.simplisell.persistence.AdPersistence;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Search {

    private AdPersistence adPersistence;

    public Search() {

        adPersistence = Services.getAdPersistence();

    }


    public List<Ad> getAllAdsByCateogry(Category category)
    {
        List<Ad> adList = new ArrayList<Ad>();

        List<Ad> allAds = adPersistence.getAds();

        for (Ad ad : allAds)
        {
            if (ad.getCategory() == category)
            {
                adList.add(ad);
            }
        }

        return adList;
    }

    public List<Ad> getAllAds()
    {
        return adPersistence.getAds();
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

    //Need multiple comparator functions per asc or desc sort due to API level 23
    Comparator<Ad> compareByPriceDesc = new Comparator<Ad>() {
        @Override
        public int compare(Ad o1, Ad o2) {
            return o1.getPrice() > o2.getPrice() ? -1 : o1.getPrice() < o2.getPrice() ? 1 : 0;
        }
    };

    Comparator<Ad> compareByPriceAsc = new Comparator<Ad>() {
        @Override
        public int compare(Ad o1, Ad o2) {
            return o1.getPrice() > o2.getPrice() ? 1 : o1.getPrice() < o2.getPrice() ? -1 : 0;
        }
    };

}
