package com.simplisell.objects;

import com.simplisell.presentation.MainActivity;

public class UserAdvertiser extends User
{
    private String firstAndLastName;
    private String email;
    private String phoneNumber;
    private String profilePhoto;
    private int numReports;


    public UserAdvertiser(String newFirstAndLastName, String newUserName, String newUserPassword,
                          String newSecurityQuestion, String newSecurityAnswer)
    {
        super(newUserName, newUserPassword, newSecurityQuestion, newSecurityAnswer);

        firstAndLastName = newFirstAndLastName;

        email = null;
        phoneNumber = null;
        profilePhoto = null;
        numReports = 0;
    }


    @Override
    public final Class logInClass()
    {
        return MainActivity.class;
    }


    public String getFirstAndLastName()
    {
        return firstAndLastName;
    }


    public String getEmail()
    {
        return email;
    }


    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    public String getProfilePhoto()
    {
        return profilePhoto;
    }


    public void setFirstAndLastName(String firstAndLastName)
    {
        this.firstAndLastName = firstAndLastName;
    }


    public void setEmail(String email)
    {
        this.email = email;
    }


    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }


    public void setProfilePhoto(String profilePhoto)
    {
        this.profilePhoto = profilePhoto;
    }

    public void incrementNumReports()
    {
        numReports++;
    }
}