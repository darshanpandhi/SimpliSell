package com.simplisell.business;

import com.simplisell.application.Services;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

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


    public User insertNewUser(User newUser)
    {
        User retValue = null;

        String userName = newUser.getUserName();

        if (!duplicateExists(userName))
        {
            retValue = userPersistence.insertUser(newUser);
        }

        return retValue;
    }


    private boolean duplicateExists(final String userName)
    {
        boolean userExists;

        if (userPersistence.getUser(userName) == null)
        {
            userExists = false;
        }
        else
        {
            userExists = true;
        }

        return userExists;
    }


    public void updatePassword(String userName, String password)
    {
        userPersistence.updatePassword(userName, password);
    }


    public void updateProfileInformation(String userName, String newFullName, String newEmail, String newPhoneNumber,
                                         String newSecurityQuestion, String newSecurityAnswer)
    {
        userPersistence.updateProfileInformation(userName, newFullName, newEmail, newPhoneNumber, newSecurityQuestion
                , newSecurityAnswer);
    }
}
