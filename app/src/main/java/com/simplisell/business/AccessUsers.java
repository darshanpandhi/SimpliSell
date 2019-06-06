package com.simplisell.business;

import com.simplisell.application.Services;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

import java.util.List;

public class AccessUsers {
    private UserPersistence userPersistence;
    private List<User> users;

    public AccessUsers() {
        userPersistence = Services.getUserPersistence();
        users = null;
    }

    public List<User> getUsers()
    {
        users =  userPersistence.getUsers();
        return users;
    }

    public String getPassword(final String userName)
    {
        return userPersistence.getPassword(userName);
    }

    public User insertNewUser(final User currentUser)
    {
        return userPersistence.insertUser(currentUser);
    }

    public User updatePassword(final String userName, final String newPassword)
    {
        return userPersistence.updatePassword(userName, newPassword);
    }
}
