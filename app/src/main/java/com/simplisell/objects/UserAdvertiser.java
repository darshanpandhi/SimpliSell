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

    public void deleteAd(String adID)
    {
        this.myAdList.remove(adID);
    }

    public List<Ad> getPostings()
    {
        return myAdList;
    }

}
