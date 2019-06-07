package com.simplisell.objects;

import java.util.ArrayList;
import java.util.List;

public class UserAdvertiser extends User
{
    private List<Ad> postings;

    public UserAdvertiser(String userName, String password)
    {
        super(userName, password);
        postings = new ArrayList<>();
    }

    public void addNewAd(Ad ad)
    {
        this.postings.add(ad);
    }

    public void deleteAd(String adID)
    {
        this.postings.remove(adID);
    }

    public List<Ad> getPostings()
    {
        return postings;
    }

}
