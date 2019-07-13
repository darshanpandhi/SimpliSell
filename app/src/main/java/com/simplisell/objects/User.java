package com.simplisell.objects;


public class User {
    private final String userName;
    private String email;
    private String phoneNumber;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private String firstAndLastName;
    private boolean admin;


    public User(String fullName, String newUserName, String newUserPassword, String newSecurityQuestion,
                String newSecurityAnswer, String newEmail, String newPhoneNumber, boolean admin) {
        userName = newUserName;
        password = newUserPassword;
        securityQuestion = newSecurityQuestion;
        securityAnswer = newSecurityAnswer;
        firstAndLastName = fullName;
        email = newEmail;
        phoneNumber = newPhoneNumber;
        this.admin = admin;
    }


    public String getUserName() {
        return userName;
    }


    public String getPassword() {
        return password;
    }


    public String getSecurityQuestion() {
        return securityQuestion;
    }


    public String getSecurityAnswer() {
        return securityAnswer;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getFirstAndLastName() {
        return firstAndLastName;
    }


    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setFirstAndLastName(String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
    }


    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }


    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public boolean isAdmin()
    {
        return admin;
    }
}
