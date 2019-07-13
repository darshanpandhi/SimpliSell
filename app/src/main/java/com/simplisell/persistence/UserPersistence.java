package com.simplisell.persistence;

import com.simplisell.objects.User;

public interface UserPersistence
{
    User getUser(final String userName);

    void updatePassword(final String userName, final String newPassword);

    void updateProfileInformation(final String userName, final String newFullName, final String newEmail,
                                  final String newPhoneNumber, final String newSecurityQuestion,
                                  final String newSecurityAnswer);

    User insertUser(final User user);

    void deleteUser(String userName);
}