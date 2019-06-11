package com.simplisell.objects;


import java.util.List;

public final class UserAdvertiser extends User
{

    private List<Ad> postings;


    public UserAdvertiser(String userName, String password)
    {
        super(userName, password);
    }

    public void addNewAd(Ad newAd)
    {
        this.postings.add(newAd);
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



