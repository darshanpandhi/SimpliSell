package com.simplisell.persistence;

import com.simplisell.objects.User;
import java.util.List;

public interface UserPersistence
{
    List<User> getUsers();
    User getUser(String userName);
    String getPassword(String userName);
    String getSecurityQuestion(String userName);
    String getSecurityAnswer(String userName);
    User insertUser(User user);
    User updatePassword(String userName, String newPassword);
}