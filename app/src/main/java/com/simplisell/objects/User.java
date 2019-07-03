package com.simplisell.objects;

import com.simplisell.presentation.MainActivity;

public abstract class User
{
    private final String userName;
    private String password;
    private String securityQuestion;
    private String securityAnswer;


    public User(String newUserName, String newUserPassword, String newSecurityQuestion, String newSecurityAnswer)
    {
        userName = newUserName;
        password = newUserPassword;
        securityQuestion = newSecurityQuestion;
        securityAnswer = newSecurityAnswer;
    }


    public String getUserName()
    {
        return userName;
    }


    public String getPassword()
    {
        return password;
    }

    public abstract Class logInClass();

    public String getSecurityQuestion()
    {
        return securityQuestion;
    }

    public String getSecurityAnswer()
    {
        return securityAnswer;
    }


    public void setSecurityAnswer(String securityAnswer)
    {
        this.securityAnswer = securityAnswer;
    }


    public void setPassword(final String newPassword)
    {
        this.password = newPassword;
    }


    public String toString()
    {
        return String.format("User: %s %s", userName, userName);
    }
}
