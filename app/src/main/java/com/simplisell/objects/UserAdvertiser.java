package com.simplisell.objects;

public class UserAdvertiser extends User
{
    private String firstAndLastName;
    private String email;
    private String phoneNumber;
    private int numReports;


    public UserAdvertiser(String newFirstAndLastName, String newUserName, String newUserPassword,
                          String newSecurityQuestion, String newSecurityAnswer, String newEmail, String newPhoneNumber,
                          int newNumReports)
    {
        super(newUserName, newUserPassword, newSecurityQuestion, newSecurityAnswer);

        firstAndLastName = newFirstAndLastName;
        email = newEmail;
        phoneNumber = newPhoneNumber;
        numReports = newNumReports;
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

    public void incrementNumReports()
    {
        numReports++;
    }

    public int getNumReports()
    {
        return numReports;
    }
}