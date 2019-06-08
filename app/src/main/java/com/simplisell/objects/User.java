package com.simplisell.objects;

import java.io.Serializable;

public abstract class User implements Serializable
{
    private final String userName;
    private String password;

    public User()
    {
        this.userName = "";
        this.password = "";
    }
    public User(final String newUserName, final String newUserPassword)
    {
        this.userName = newUserName;
        this.password = newUserPassword;
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
