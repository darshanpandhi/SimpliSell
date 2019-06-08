package com.simplisell.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAdvertiser extends User implements Serializable
{
    private List<String> postings;

    public UserAdvertiser(String userName, String password)
    {
        super(userName, password);
        postings = new ArrayList<>();
    }

    public void addNewAd(String ad)
    {
        this.postings.add(ad);
    }

    public void deleteAd(String adID)
    {
        this.postings.remove(adID);
    }

    public List<String> getPostings()
    {
        return postings;
    }

}
