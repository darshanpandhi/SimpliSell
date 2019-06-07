package com.simplisell.objects;

import java.util.ArrayList;
import java.util.List;

public class UserAdvertiser extends User
{
    private List<Ad> myAdList;

    public UserAdvertiser(String userName, String password)
    {
        super(userName, password);
        myAdList = new ArrayList<>();
    }

    public void insertAdToList(Ad ad)
    {
        this.myAdList.add(ad);
    }

    public void deleteAd(int adId)
    {
        this.myAdList.remove(adId);
    }

    public List<Ad> getPostings()
    {
        return myAdList;
    }

}
