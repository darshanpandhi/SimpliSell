package com.simplisell.objects;

public class User
{
    private final String userName;
    private String password;
    private String securityQuestion;
    private String securityAnswer;


    public User()
    {
        userName = "";
        password = "";
        securityQuestion = "";
        securityAnswer = "";
    }


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


    public String getSecurityQuestion()
    {
        return securityQuestion;
    }


    public String getSecurityAnswer()
    {
        return securityAnswer;
    }


    public void setPassword(final String newPassword)
    {
        this.password = newPassword;
    }


    public String toString()
    {
        return String.format("User: %s %s %s", userName, userName);
    }
}
