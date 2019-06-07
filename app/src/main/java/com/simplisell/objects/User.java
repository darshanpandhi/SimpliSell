package com.simplisell.objects;

import java.util.ArrayList;
import java.util.Date;

public class User
{
    private final String userName;
    private String password;
    private final ArrayList<Ad> myAdList;


    public User()
    {
        this.userName = "";
        this.password = "";
        myAdList = new ArrayList<Ad>();
    }
    public User(final String newUserName, final String newUserPassword)
    {
        this.userName = newUserName;
        this.password = newUserPassword;
        myAdList = new ArrayList<Ad>();
    }

    // method called when user creates a new ad. This ad will be stored in user's myAdList (call to
    // insertAdToList() method)
    private void createAd(final AdType adType, final Category category, final String title, final
    String description, final double price)
    {
        final User adOwner = this;
        final Date adPostDate = new Date();

        Ad newAd = new Ad(adOwner, adType, category, adPostDate, title, description, price);

        insertAdToList(newAd);
    }

    // Helper method to add a newly created ad to user's list of ads
    private void insertAdToList(final Ad newAd)
    {
        myAdList.add(newAd);
    }

    public String getUserName()
    {

        return userName;
    }

    public String getPassword()
    {

        return password;
    }

    public void setPassword(final String newPassword)
    {
        this.password = newPassword;
    }

    public String toString()
    {
        return String.format("User: %s %s %s", userName, userName);
    }
}
