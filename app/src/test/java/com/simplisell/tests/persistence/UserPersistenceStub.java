package com.simplisell.tests.persistence;

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
        user = new User("Bob Marley", "Bob", "123456", "What is your favourite color", "Red", null, null);
        users.add(user);
        user = new User("Allice Wonderland", "Allice", "111111", "What is your favourite color", "Green", null, null);
        users.add(user);
        user = new User("Jay Petr", "Jay", "222222", "What is your mother\'s maiden name?", "Elsa", null, null);
        users.add(user);
    }


    @Override
    public User getUser(final String userName)
    {
        User user = findUser(userName);
        return user;
    }

    @Override
    public User insertUser(final User user)
    {
        users.add(user);
        return user;
    }


    @Override
    public void updatePassword(final String userName, final String newPassword)
    {
        User user = findUser(userName);
        if (user != null)
        {
            user.setPassword(newPassword);
        }
    }


    @Override
    public void updateProfileInformation(String userName, String newFullName, String newEmail, String newPhoneNumber, String newSecurityQuestion, String newSecurityAnswer)
    {
        User user = getUser(userName);
        user.setFirstAndLastName(newFullName);
        user.setEmail(newEmail);
        user.setPhoneNumber(newPhoneNumber);
        user.setSecurityQuestion(newSecurityQuestion);
        user.setSecurityAnswer(newSecurityAnswer);
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

    public void deleteUser(String userName)
    {

        for(int i = 0; i<users.size(); i++)
        {
            if(users.get(i).getUserName().equals(userName))
            {
                users.remove(i);
            }
        }

    }

}
