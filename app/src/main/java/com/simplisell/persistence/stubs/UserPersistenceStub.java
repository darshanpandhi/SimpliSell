package com.simplisell.persistence.stubs;

import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.List;

public class UserPersistenceStub implements UserPersistence
{
    private List<User> users;


    public UserPersistenceStub()
    {
        this.users = new ArrayList<>();
        User user;
        user = new User("Bob", "123456", "What is your favourite color", "Red");
        users.add(user);
        user = new User("Allice", "123456", "What is your favourite color", "Green");
        users.add(user);
        user = new User("Jay", "123456", "What is your favourite color", "Blue");
        users.add(user);
    }


    @Override
    public List<User> getUsers()
    {
        return users;
    }


    @Override
    public User getUser(final String userName)
    {
        User user = findUser(userName);
        return user;
    }


    @Override
    public String getPassword(final String userName)
    {
        User user = findUser(userName);
        String password = null;
        if (user != null)
        {
            password = user.getPassword();
        }
        return password;
    }


    @Override
    public String getSecurityQuestion(String userName)
    {
        User user = findUser(userName);
        String securityQuestion = null;
        if (user != null)
        {
            securityQuestion = user.getSecurityQuestion();
        }
        return securityQuestion;
    }


    @Override
    public String getSecurityAnswer(String userName)
    {
        User user = findUser(userName);
        String securityQuestion = null;
        if (user != null)
        {
            securityQuestion = user.getSecurityQuestion();
        }
        return securityQuestion;
    }


    @Override
    public User insertUser(final User user)
    {
        users.add(user);
        return user;
    }


    @Override
    public User updatePassword(final String userName, final String newPassword)
    {
        User user = findUser(userName);
        if (user != null)
        {
            user.setPassword(newPassword);
        }
        return user;
    }


    private User findUser(final String userName)
    {
        User user = null;

        if (userName != null)
        {

            boolean foundUser = false;
            for (int i = 0; i < users.size() && !foundUser; i++)
            {
                user = users.get(i);
                if (user.getUserName().equals(userName))
                {
                    foundUser = true;
                }
            }
            if (!foundUser)
            {
                user = null;
            }
        }

        return user;
    }
}
