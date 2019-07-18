package com.simplisell.tests.business;

import com.simplisell.business.ValidPasswordChecker;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidPasswordCheckerTest
{
    @Test
    public void testValidPasswordLength()
    {
        System.out.println("\nStarting testValidPassword: password has length greater than or equal to 6 and less " +
                "than or equal to 12");

        String password = "123456";
        boolean isValid = ValidPasswordChecker.validPassword(password);
        assertTrue(isValid);
        password = "qwertyuiopas";
        isValid = ValidPasswordChecker.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has length greater than or equal to 6 and less than " +
                "or equal to 12");
    }


    @Test
    public void testValidPasswordLowerCaseLetters()
    {
        System.out.println("\nStarting testValidPassword: password has lowercase letters");

        String password = "abcdefghijkl";
        boolean isValid = ValidPasswordChecker.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has lowercase letters");
    }


    @Test
    public void testValidPasswordUpperCaseLetters()
    {
        System.out.println("\nStarting testValidPassword: password has uppercase letters");

        String password = "ABCDEFGH";
        boolean isValid = ValidPasswordChecker.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has uppsercase letters");
    }


    @Test
    public void testValidPasswordNumbers()
    {
        System.out.println("\nStarting testValidPassword: password has numbers");

        String password = "123456789";
        boolean isValid = ValidPasswordChecker.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has number");
    }


    @Test
    public void testValidPasswordLettersAndNumMix()
    {
        System.out.println("\nStarting testValidPassword: password has letters and numbers");

        String password = "aBc1d34";
        boolean isValid = ValidPasswordChecker.validPassword(password);

        assertTrue(isValid);

        System.out.println("Finished testValidPassword: password has letters and numbers");
    }


    @Test
    public void testInvalidPasswordLength()
    {
        System.out.println("\nStarting testValidPassword: password has length less than 6 or greater than to 12");

        String password = "12345";
        boolean isValid = ValidPasswordChecker.validPassword(password);
        assertFalse(isValid);
        password = "abcdefghijklm";
        isValid = ValidPasswordChecker.validPassword(password);

        assertFalse(isValid);

        System.out.println("Finished testValidPassword: password has length less than 6 or greater than to 12");
    }


    @Test
    public void testInvalidPasswordCharacters()
    {
        System.out.println("\nStarting testValidPassword: password has an invalid character (not a letter or number)");

        String password = "!_&abcd";
        boolean isValid = ValidPasswordChecker.validPassword(password);
        assertFalse(isValid);
        password = "aaaaaa  ";
        isValid = ValidPasswordChecker.validPassword(password);

        assertFalse(isValid);

        System.out.println("Finished testValidPassword: password has an invalid character (not a letter or number)");
    }
}
