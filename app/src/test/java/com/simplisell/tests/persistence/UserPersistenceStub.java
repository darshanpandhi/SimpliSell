package com.simplisell.tests.persistence;

import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.List;

public class UserPersistenceStub implements UserPersistence
{
    private List<User> userList;


    public UserPersistenceStub()
    {
        userList = new ArrayList<>();

        User newUser;

        newUser = new User("Bob Marley", "Bob", "123456",
                "What is your favourite color", "Red", null,
                null, false);
        userList.add(newUser);

        newUser = new User("Allice Wonderland", "Allice",
                "111111", "What is your favourite color", "Green",
                null, null, false);
        userList.add(newUser);

        newUser = new User("Jay Petr", "Jay", "222222",
                "What is your mother\'s maiden name?", "Elsa", null,
                null, false);
        userList.add(newUser);

        newUser = new User("Ronak the dude","Ronak", "admin",
                "What is your favourite color", "Black","","",true);
        userList.add(newUser);
    }


    public User getUser(final String userName)
    {
        User user = findUser(userName);

        return user;
    }


    @Override
    public User insertUser(final User User)
    {
        userList.add(User);
        return User;
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
    public void updateProfileInformation(final String userName, final String newFullName, final String newEmail,
                                         final String newPhoneNumber, final String newSecurityQuestion,
                                         final String newSecurityAnswer)
    {
        User User = getUser(userName);

        if (User != null)
        {
            User.setFirstAndLastName(newFullName);
            User.setEmail(newEmail);
            User.setPhoneNumber(newPhoneNumber);
            User.setSecurityQuestion(newSecurityQuestion);
            User.setSecurityAnswer(newSecurityAnswer);
        }
    }


    private User findUser(final String userName)
    {
        User requiredUser = null;

        if (userName != null)
        {
            boolean foundUser = false;
            User currentUser;

            for (int i = 0; i < userList.size() && !foundUser; i++)
            {
                currentUser = userList.get(i);

                if (currentUser.getUserName().equals(userName))
                {
                    foundUser = true;
                    requiredUser = currentUser;
                }
            }
        }

        return requiredUser;
    }

    public void deleteUser(String userName)
    {

        for(int i = 0; i<userList.size(); i++)
        {
            if(userList.get(i).getUserName().equals(userName))
            {
                userList.remove(i);
            }
        }

    }

}
