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

    public List<User> getAllUsers() {
        return userPersistence.getUsers();
    }

    public User getUser(String userName) {
        return userPersistence.getUser(userName);
    }

    public User insertNewUser(User currentUser) {
        User newUser = null;
        String userName = currentUser.getUserName();
        if (!userNameExists(userName)) {
            newUser = currentUser;
            userPersistence.insertUser(newUser);
        }
        return newUser;
    }

    public boolean correctPassword(String userName, String password) {
        boolean match = false;
        User user = userPersistence.getUser(userName);
        if (user != null) {
            match = user.getPassword().equals(password);
        }
        return match;
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

    public String getPassword(String userName) {
        return userPersistence.getPassword(userName);
    }

    public User updatePassword(String userName, String newPassword) {
        return userPersistence.updatePassword(userName, newPassword);
    }

    private boolean userNameExists(String userName) {
        User user = userPersistence.getUser(userName);
        boolean userNameExists = user != null;
        return userNameExists;
    }
}
