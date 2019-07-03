package com.simplisell.objects;

import com.simplisell.presentation.useradminactivity.AdminHomeScreen;

public class UserAdmin extends User
{
    public UserAdmin(String newUserName, String newUserPassword, String newSecurityQuestion, String newSecurityAnswer)
    {
        super(newUserName, newUserPassword, newSecurityQuestion, newSecurityAnswer);
    }

    @Override
    public final Class logInClass()
    {
        return AdminHomeScreen.class;
    }
}