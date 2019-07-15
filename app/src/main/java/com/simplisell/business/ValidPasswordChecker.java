package com.simplisell.business;

public class ValidPasswordChecker {

    public static boolean validPassword(final String password)
    {
        return !passwordHasInvalidCharacters(password) && validPasswordLength(password);
    }


    private static boolean validPasswordLength(final String password)
    {
        int minPasswordSize = 6;
        int maxPasswordSize = 12;
        int passwordLength = password.length();
        return passwordLength >= minPasswordSize && passwordLength <= maxPasswordSize;
    }


    private static boolean passwordHasInvalidCharacters(final String password)
    {
        boolean containsInvalidCharacters = false;
        for (int i = 0; i < password.length() && !containsInvalidCharacters; i++)
        {
            char character = password.charAt(i);
            if (!isValid(character))
            {
                containsInvalidCharacters = true;
            }
        }
        return containsInvalidCharacters;
    }

    private static boolean isValid(final char character)
    {
        int asciiForCharacter = (int) character;
        boolean isNum = asciiForCharacter >= '0' && asciiForCharacter <= '9';
        boolean isLowerCaseLetter = asciiForCharacter >= 'a' && asciiForCharacter <= 'z';
        boolean isUpperCaseLetter = asciiForCharacter >= 'A' && asciiForCharacter <= 'Z';
        boolean isLetter = isLowerCaseLetter || isUpperCaseLetter;
        return isNum || isLetter;
    }

}
