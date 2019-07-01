package com.simplisell.business;

import com.simplisell.application.Services;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

import java.util.List;

public class AccessUsers
{
    private UserPersistence userPersistence;



    public AccessUsers()
    {
        userPersistence = Services.getUserPersistence();
    }

    public AccessUsers(final UserPersistence userPersistence)
    {
        this.userPersistence = userPersistence;
    }

    public User getUser(String userName)
    {
        return userPersistence.getUser(userName);
    }

    public List<User> getAllUsers()
    {
        return userPersistence.getUsers();
    }

    public User insertNewUser(User currentUser)
    {
        User newUser = null;
        String userName = currentUser.getUserName();
        boolean uniqueUserName = userPersistence.getUser(userName) == null;
        if (uniqueUserName)
        {
            newUser = currentUser;
            userPersistence.insertUser(newUser);
        }
        return newUser;
    }

    public void reportUser(String userName)
    {
        userPersistence.reportUser(userName);
    }
}
