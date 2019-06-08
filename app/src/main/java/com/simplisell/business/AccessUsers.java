package com.simplisell.business;

import com.simplisell.application.Services;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

import java.util.List;

public class AccessUsers {
    private UserPersistence userPersistence;

    public AccessUsers() {
        userPersistence = Services.getUserPersistence();
    }

    public List<User> getAllUsers()
    {
        return userPersistence.getUsers();
    }

    public User getUser(final String userName)
    {
        return userPersistence.getUser(userName);
    }

    public User insertNewUser(final User currentUser)
    {
        User newUser = null;
        String userName = currentUser.getUserName();
        if (!userNameExists(userName))
        {
            newUser = currentUser;
            userPersistence.insertUser(newUser);
        }
        return newUser;
    }

    public boolean correctPassword(final String userName, final String password)
    {
        boolean match = false;
        User user = userPersistence.getUser(userName);
        if (user != null)
        {
            match = user.getPassword().equals(password);
        }
        return match;
    }

    public String getPassword(final String userName)
    {
        return userPersistence.getPassword(userName);
    }

    public User updatePassword(final String userName, final String newPassword)
    {
        return userPersistence.updatePassword(userName, newPassword);
    }

    private boolean userNameExists(final String userName)
    {
        User user = userPersistence.getUser(userName);
        boolean userNameExists = user != null;
        return userNameExists;
    }
}
