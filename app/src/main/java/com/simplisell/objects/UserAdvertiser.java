package com.simplisell.objects;

import com.simplisell.presentation.MainActivity;

public class UserAdvertiser extends User
{
    public UserAdvertiser(String newUserName, String newUserPassword, String newSecurityQuestion, String newSecurityAnswer)
    {
        super(newUserName, newUserPassword, newSecurityQuestion, newSecurityAnswer);
    }


    @Override
    public final Class logInClass()
    {
        return MainActivity.class;
    }
}