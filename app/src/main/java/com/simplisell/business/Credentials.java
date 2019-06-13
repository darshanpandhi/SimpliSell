package com.simplisell.business;

import com.simplisell.application.Services;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;

public class Credentials {
    private UserPersistence userPersistence;

    public Credentials()
    {
        userPersistence = Services.getUserPersistence();
    }

    public User authenticate(String userName, String password)
    {
        User user = userPersistence.getUser(userName);
        boolean validUser = user != null && correctPassword(userName, password);
        if (!validUser) {
            user = null;
        }
        return user;
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

    public void updatePassword(String userName, String newPassword)
    {
        userPersistence.updatePassword(userName, newPassword);
    }

    public boolean validPassword(final String password)
    {
        return !passwordHasInvalidCharacters(password) && validPasswordLength(password);
    }

    private boolean validPasswordLength(final String password)
    {
        int minPasswordSize = 6;
        int maxPasswordSize = 12;
        int passwordLength = password.length();
        return passwordLength >= minPasswordSize && passwordLength <= maxPasswordSize;
    }

    private boolean passwordHasInvalidCharacters(final String password)
    {
        boolean containsInvalidCharacters = false;
        for (int i = 0; i < password.length() && !containsInvalidCharacters; i++)
        {
            char character = password.charAt(i);
            if (!isNum(character) && !isLetter(character))
            {
                containsInvalidCharacters = true;
            }
        }
        return containsInvalidCharacters;
    }

    private boolean isNum(final char character)
    {
        int asciiForCharacter = (int)character;
        return asciiForCharacter >= '0' && asciiForCharacter <= '9';
    }

    private boolean isLetter(final char character)
    {
        int asciiForCharacter = (int)character;
        boolean isLowerCaseLetter = asciiForCharacter >= 'a' && asciiForCharacter <= 'z';
        boolean isUpperCaseLetter = asciiForCharacter >= 'A' && asciiForCharacter <= 'Z';
        return isLowerCaseLetter || isUpperCaseLetter;
    }

    private boolean correctPassword(String userName, String password) {
        boolean match = false;
        User user = userPersistence.getUser(userName);
        if (user != null) {
            match = user.getPassword().equals(password);
        }
        return match;
    }

}