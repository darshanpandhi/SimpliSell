package com.simplisell.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAdvertiser extends User implements Serializable
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
