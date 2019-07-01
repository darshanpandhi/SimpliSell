package com.simplisell.persistence;

import com.simplisell.objects.User;

import java.util.List;

public interface UserPersistence
{
    List<User> getUsers();

    User getUser(String userName);

    User insertUser(User user);

    void updatePassword(String userName, String newPassword);
}