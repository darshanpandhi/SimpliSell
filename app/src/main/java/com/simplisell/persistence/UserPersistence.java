package com.simplisell.persistence;

import com.simplisell.objects.User;

import java.util.List;

public interface UserPersistence
{
    List<User> getUsers();
    User getUser(final String userName);
    String getPassword(final String userName);
    User insertUser(final User user);
    User updatePassword(final String userName, final String newPassword);
}
