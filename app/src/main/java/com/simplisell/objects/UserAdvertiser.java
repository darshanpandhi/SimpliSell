package com.simplisell.objects;

import java.util.ArrayList;
import java.util.List;

public class UserAdvertiser extends User
{
    private ArrayList<Ad> myAdList;

    public UserAdvertiser(String userName, String password)
    {
        super(userName, password);
        myAdList = new ArrayList<>();
    }

    private void insertAdToList(final Ad newAd)
    {
        myAdList.add(newAd);
    }

    public List<Ad> getPostings()
    {
        return myAdList;
    }

}
