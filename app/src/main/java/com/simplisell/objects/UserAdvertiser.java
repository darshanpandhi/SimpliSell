package com.simplisell.objects;


import java.util.List;

public final class UserAdvertiser extends User
{

    private List<String> postings;


    public UserAdvertiser(String userName, String password)
    {
        super(userName, password);
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



