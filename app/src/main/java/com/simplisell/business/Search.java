package com.simplisell.business;

import com.simplisell.objects.Ad;
import com.simplisell.objects.Category;
import com.simplisell.objects.User;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Search
{

    private final AccessAds adInterface;

    private final AccessUsers userInterface;

    public Search()
    {
        adInterface = new AccessAds();
        userInterface = new AccessUsers();
    }


    public List<Ad> getAllAdsByCategory(Category category)
    {
        List<Ad> adList = new ArrayList<Ad>();

        List<Ad> allAds = adInterface.getAllAds();

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

        List<Ad> allAds = adInterface.getAllAds();

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

        List<Ad> allAds = adInterface.getAllAds();

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

    public List<User> getReportedUsers()
    {
        List<User> userList = new ArrayList<User>();

        List<User> allUsers = userInterface.getAllUsers();

        int minNumberOfReports = 3;

        for (User user: allUsers)
        {
            if (user.getNumReports() >= minNumberOfReports)
            {
                userList.add(user);
            }
        }
        return userList;
    }

    public List<Ad> getAllAds()
    {
        return adInterface.getAllAds();
    }

    public List<User> getAllUsers()
    {
        return userInterface.getAllUsers();
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
}
