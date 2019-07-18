package com.simplisell.acceptancetests;


import android.os.SystemClock;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.simplisell.R;
import com.simplisell.application.Services;
import com.simplisell.persistence.UserPersistence;
import com.simplisell.persistence.hsqldb.PersistenceException;
import com.simplisell.presentation.SplashScreen;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class LoginFeatureAcceptanceTest
{

    private UserPersistence userPersistence;


    @Rule
    public ActivityTestRule<SplashScreen> activityRule = new ActivityTestRule<>(SplashScreen.class);

    @After
    public void cleanUp()
    {

        userPersistence = Services.getUserPersistence();

        try
        {
            userPersistence.deleteUser("Bob2");
        }
        catch (PersistenceException e)
        {
            System.out.println("Failed to delete \'Bob2\' from the users because the user didnt register");
        }
    }



    @Test
    public void loginFeatureTest()
    {
        SystemClock.sleep(1500);

        // Guest Login - Guest can view ads without logging In
        onView(ViewMatchers.withId(R.id.view_pager_mainActivity)).perform(click());
        onView(withId(R.id.textView_viewAdOU_title)).check(matches(withText("Analysis of Algorithms")));
        onView(withId(R.id.textView_viewAdOU_price)).check(matches(withText("$20.0")));
        onView(withId(R.id.textView_viewAdOU_description)).check(matches(withText("Book for COMP2080 and COMP3170")));
        SystemClock.sleep(2500);
        pressBack();
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onView(withId(R.id.signUp_btn_onLogin)).perform(click());


        // Test sign up when all fields are empty.
        onView(withId(R.id.button_signUp_registerbutton)).perform(click());
        SystemClock.sleep(2500);



        // Test Registration click when trying to register when user already exists
        onView(withId(R.id.editText_name_registrationName)).perform(typeText("Bob"));
        onView(withId(R.id.editText_name_user)).perform(typeText("Bob"));
        onView(withId(R.id.editText_name_registrationPass)).perform(typeText("123456"));
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(typeText("123456"));
        onView(withId(R.id.spinner_signUp_securityQuestions)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(typeText("secret"));
        pressBack();
        onView(withId(R.id.button_signUp_registerbutton)).perform(click());
        SystemClock.sleep(2500);



        // Test Registration click when trying to register with a very small password
        onView(withId(R.id.editText_name_registrationName)).perform(clearText());
        onView(withId(R.id.editText_name_user)).perform(clearText());
        onView(withId(R.id.editText_name_registrationPass)).perform(clearText());
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(clearText());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(clearText());

        onView(withId(R.id.editText_name_registrationName)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_name_user)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_name_registrationPass)).perform(typeText("1"));
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(typeText("1"));
        onView(withId(R.id.spinner_signUp_securityQuestions)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(typeText("secret"));
        pressBack();
        onView(withId(R.id.button_signUp_registerbutton)).perform(click());
        SystemClock.sleep(2500);



        // Test Registration of passwords not matching
        onView(withId(R.id.editText_name_registrationName)).perform(clearText());
        onView(withId(R.id.editText_name_user)).perform(clearText());
        onView(withId(R.id.editText_name_registrationPass)).perform(clearText());
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(clearText());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(clearText());

        onView(withId(R.id.editText_name_registrationName)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_name_user)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_name_registrationPass)).perform(typeText("1234567890"));
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(typeText("1"));
        onView(withId(R.id.spinner_signUp_securityQuestions)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(typeText("secret"));
        pressBack();
        onView(withId(R.id.button_signUp_registerbutton)).perform(click());
        SystemClock.sleep(2500);



        // Test Registration of without security question
        onView(withId(R.id.editText_name_registrationName)).perform(clearText());
        onView(withId(R.id.editText_name_user)).perform(clearText());
        onView(withId(R.id.editText_name_registrationPass)).perform(clearText());
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(clearText());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(clearText());
        onView(withId(R.id.editText_name_registrationName)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_name_user)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_name_registrationPass)).perform(typeText("111111"));
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(typeText("111111"));
        onView(withId(R.id.spinner_signUp_securityQuestions)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(typeText("secret"));
        pressBack();
        onView(withId(R.id.button_signUp_registerbutton)).perform(click());
        SystemClock.sleep(2500);



        // Test normal registration with good data
        onView(withId(R.id.editText_name_registrationName)).perform(clearText());
        onView(withId(R.id.editText_name_user)).perform(clearText());
        onView(withId(R.id.editText_name_registrationPass)).perform(clearText());
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(clearText());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(clearText());
        onView(withId(R.id.editText_name_registrationName)).perform(typeText("Bob the dude"));
        onView(withId(R.id.editText_name_user)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_name_registrationPass)).perform(typeText("111111"));
        onView(withId(R.id.editText_name_registrationConfirmPass)).perform(typeText("111111"));
        onView(withId(R.id.spinner_signUp_securityQuestions)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.editText_name_registrationSecurityAnswer)).perform(typeText("secret"));
        pressBack();
        onView(withId(R.id.button_signUp_registerbutton)).perform(click());
        SystemClock.sleep(2500);
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onView(withText("Logout")).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());



        // Test Login when username and password fields are empty.
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1500);



        // Test login click when password field is empty
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob2"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1500);



        // Test login click when password is wrong.
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("123456"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1500);




        // Test login when username box is empty
        onView(withId(R.id.editText_login_enterUserName)).perform(clearText());
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1500);



        // Test correct credintals
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_login_enterPassword)).perform(clearText());
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("111111"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        SystemClock.sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.profile_list_view)).atPosition(0).perform(click());
        onView(withId(R.id.profileEditBoxName6)).check(matches(withHint("Bob the dude")));
        SystemClock.sleep(1000);
        pressBack();
        SystemClock.sleep(500);
        onView(withText("Logout")).perform(click());
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onView(withId(R.id.forgotPasswordTextView)).perform(click());



        // click on reset password without doing anything
        onView(withId(R.id.getSecuriityQuestionButton)).perform(click());
        SystemClock.sleep(1500);



        // click on reset password without doing anything
        onView(withId(R.id.answeredSecurityQuestionBtn)).perform(click());
        SystemClock.sleep(1500);



        // type text and then click on get security question then click on reset password
        onView(withId(R.id.editText_forgetPassword_userName)).perform(typeText("Bob2"));
        onView(withId(R.id.getSecuriityQuestionButton)).perform(click());
        pressBack();
        onView(withId(R.id.answeredSecurityQuestionBtn)).perform(click());
        SystemClock.sleep(1500);


        // type security answer
        onView(withId(R.id.editText_forgetPassword_securityAnswer)).perform(typeText("I have no idea"));
        pressBack();
        onView(withId(R.id.answeredSecurityQuestionBtn)).perform(click());
        SystemClock.sleep(1000);


        // type security answer
        onView(withId(R.id.editText_forgetPassword_securityAnswer)).perform(clearText());
        onView(withId(R.id.editText_forgetPassword_securityAnswer)).perform(typeText("secret"));
        pressBack();
        onView(withId(R.id.answeredSecurityQuestionBtn)).perform(click());
        onView(withHint(" New Password")).perform(typeText("1234"));
        onView(withHint(" Confirm New Password")).perform(typeText("1234"));
        onView(withText("OK")).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.answeredSecurityQuestionBtn)).perform(click());



        // reset the password with normal case
        SystemClock.sleep(1000);
        onView(withHint(" New Password")).perform(typeText("1111111111"));
        onView(withHint(" Confirm New Password")).perform(typeText("1111111111"));
        onView(withText("OK")).perform(click());
        SystemClock.sleep(1000);



        // Test old credintals
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("123456"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1500);



        // Test correct credintals
        onView(withId(R.id.editText_login_enterPassword)).perform(clearText());
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("1111111111"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.profile_list_view)).atPosition(0).perform(click());
        onView(withId(R.id.profileEditBoxName6)).check(matches(withHint("Bob the dude")));
        pressBack();
        onView(withText("Logout")).perform(click());




    }

}
