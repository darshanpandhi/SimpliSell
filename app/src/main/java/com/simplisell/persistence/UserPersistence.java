package com.simplisell.persistence;

import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;

import java.util.List;

public interface UserPersistence
{
    User getUser(final String userName);

    UserAdvertiser insertUserAdvertiser(final UserAdvertiser userAdvertiser);

    void updatePassword(final String userName, final String newPassword);

    void updateProfileInformation(final String userName, final String newFullName, final String newEmail,
                                  final String newPhoneNumber, final String newSecurityQuestion,
                                  final String newSecurityAnswer);

    UserAdvertiser getUserAdvertiser(final String userName);

    void deleteUser(String userName);
}