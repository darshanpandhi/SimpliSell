package com.simplisell.objects;

public class User
{
    private final String userName;
    private final String password;

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

    public String toString()
    {
        return String.format("User: %s %s %s", userName, userName);
    }
}
