package com.simplisell.persistence;

import com.simplisell.objects.User;

import java.util.List;

public interface UserPersistence
{
    User getUser(String userName);

    User insertUser(User user);

    void updatePassword(String userName, String newPassword);

    void updateProfileInformation(String userName, String newFullName, String newEmail, String newPhoneNumber,
                                  String newSecurityQuestion, String newSecurityAnswer);

    void updateProfileImage(String userName, String profilePhoto);

    void deleteUser(String userName);
}