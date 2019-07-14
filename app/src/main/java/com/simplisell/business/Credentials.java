package com.simplisell.business;

import com.simplisell.application.Services;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

public class Credentials
{
    private UserPersistence userPersistence;


    public Credentials()
    {
        userPersistence = Services.getUserPersistence();
    }


    public Credentials(UserPersistence userPersistence)
    {
        this.userPersistence = userPersistence;
    }


    public User correctPassword(String userName, String password)
    {
        User user = userPersistence.getUser(userName);

        boolean validUser = user != null && correctPassword(user, password);

        if (!validUser)
        {
            user = null;
        }
        return user;
    }


    public boolean correctSecurityAnswer(String userName, String securityAnswer)
    {
        boolean correctAnswer = false;
        User user = userPersistence.getUser(userName);
        {
            if (user != null)
            {
                correctAnswer = user.getSecurityAnswer().equalsIgnoreCase(securityAnswer);
            }
        }
        return correctAnswer;
    }


    private boolean correctPassword(User user, String password)
    {
        boolean match = false;

        if (user != null)
        {
            match = user.getPassword().equals(password);
        }

        return match;
    }
}
