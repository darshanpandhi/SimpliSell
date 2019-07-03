package com.simplisell.tests.persistence;

import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;
import com.simplisell.objects.UserAdmin;
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
<<<<<<< HEAD:app/src/main/java/com/simplisell/persistence/stubs/UserPersistenceStub.java



        user = new UserAdvertiser("bob the bobber", "Bob", "123456", "What is your favourite color", "Red");
||||||| merged common ancestors:app/src/main/java/com/simplisell/persistence/stubs/UserPersistenceStub.java
        user = new User("bob the bobber","Bob", "123456", "What is your favourite color", "Red");
=======
        user = new User("Bob Marley", "Bob", "123456", "What is your favourite color", "Red", 0, null, null, null);
>>>>>>> reportSystem:app/src/test/java/com/simplisell/tests/persistence/UserPersistenceStub.java
        users.add(user);
<<<<<<< HEAD:app/src/main/java/com/simplisell/persistence/stubs/UserPersistenceStub.java
        user = new UserAdvertiser("allice the cook", "Allice", "123456", "What is your favourite color", "Green");
||||||| merged common ancestors:app/src/main/java/com/simplisell/persistence/stubs/UserPersistenceStub.java
        user = new User("allice the cook","Allice", "123456", "What is your favourite color", "Green");
=======
        user = new User("Allice Wonderland", "Allice", "111111", "What is your favourite color", "Green", 0, null, null, null);
>>>>>>> reportSystem:app/src/test/java/com/simplisell/tests/persistence/UserPersistenceStub.java
        users.add(user);
<<<<<<< HEAD:app/src/main/java/com/simplisell/persistence/stubs/UserPersistenceStub.java
        user = new UserAdvertiser("Jay the Don", "Jay", "123456", "What is your favourite color", "Blue");
        users.add(user);

        user = new UserAdmin("Ronak", "admin", "What is your favourite color", "Black");
||||||| merged common ancestors:app/src/main/java/com/simplisell/persistence/stubs/UserPersistenceStub.java
        user = new User("Jay the Don","Jay", "123456", "What is your favourite color", "Blue");
=======
        user = new User("Jay Petr", "Jay", "222222", "What is your mother\'s maiden name?", "Elsa", 0, null, null, null);
>>>>>>> reportSystem:app/src/test/java/com/simplisell/tests/persistence/UserPersistenceStub.java
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
    public void reportUser(final String userName)
    {
        User reportedUser = findUser(userName);
        reportedUser.incrementNumReports();
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

    @Override
    public void updateProfileImage(String userName, String profilePhoto)
    {
        User user = getUser(userName);
        user.setProfilePhoto(profilePhoto);
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