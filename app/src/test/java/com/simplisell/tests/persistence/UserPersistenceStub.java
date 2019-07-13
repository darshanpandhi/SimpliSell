package com.simplisell.tests.persistence;

import com.simplisell.objects.User;
import com.simplisell.objects.UserAdmin;
import com.simplisell.objects.UserAdvertiser;
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

        newUser = new UserAdvertiser("Bob Marley", "Bob", "123456", "What is your favourite color", "Red", null, null
                , null, 0);
        userList.add(newUser);

        newUser = new UserAdvertiser("Allice Wonderland", "Allice", "111111", "What is your favourite color", "Green"
                , null, null, null, 0);
        userList.add(newUser);

        newUser = new UserAdvertiser("Jay Petr", "Jay", "222222", "What is your mother\'s maiden name?", "Elsa", null
                , null, null, 0);
        userList.add(newUser);

        newUser = new UserAdmin("Ronak", "admin", "What is your favourite color", "Black");
        userList.add(newUser);
    }


    public User getUser(final String userName)
    {
        User user = findUser(userName);

        return user;
    }


    @Override
    public UserAdvertiser insertUserAdvertiser(final UserAdvertiser userAdvertiser)
    {
        userList.add(userAdvertiser);
        return userAdvertiser;
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
    public void reportUserAdvertiser(final String userName)
    {
        UserAdvertiser reportedUserAdvertiser = getUserAdvertiser(userName);

        if (reportedUserAdvertiser != null)
        {
            reportedUserAdvertiser.incrementNumReports();
        }
    }


    @Override
    public void updateProfileInformation(final String userName, final String newFullName, final String newEmail,
                                         final String newPhoneNumber, final String newSecurityQuestion,
                                         final String newSecurityAnswer)
    {
        UserAdvertiser userAdvertiser = getUserAdvertiser(userName);

        if (userAdvertiser != null)
        {
            userAdvertiser.setFirstAndLastName(newFullName);
            userAdvertiser.setEmail(newEmail);
            userAdvertiser.setPhoneNumber(newPhoneNumber);
            userAdvertiser.setSecurityQuestion(newSecurityQuestion);
            userAdvertiser.setSecurityAnswer(newSecurityAnswer);
        }
    }


    @Override
    public void updateProfileImage(final String userName, final String profilePhoto)
    {
        UserAdvertiser userAdvertiser = getUserAdvertiser(userName);

        if (userAdvertiser != null)
        {
            userAdvertiser.setProfilePhoto(profilePhoto);
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


    public final UserAdvertiser getUserAdvertiser(final String userName)
    {
        User requiredUser = findUser(userName);
        UserAdvertiser requiredUserAdvertiser = null;

        if (requiredUser instanceof UserAdvertiser)
        {
            requiredUserAdvertiser = (UserAdvertiser) requiredUser;
        }

        return requiredUserAdvertiser;
    }
}
