package com.simplisell;


import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.simplisell.application.Services;
import com.simplisell.persistence.UserPersistence;
import com.simplisell.persistence.hsqldb.PersistenceException;
import com.simplisell.presentation.SplashScreen;

import org.junit.After;
import org.junit.Before;
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
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class AllAcceptanceTest
{

    UserPersistence userPersistence;


    @Rule
    public ActivityTestRule<SplashScreen> activityRule = new ActivityTestRule<>(SplashScreen.class);

    @Before
    public void setupDatabase()
    {

        userPersistence = Services.getUserPersistence();

    }


    @After
    public void cleanUp()
    {


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
    public void loginFeatureAndProfileFeatureTest()
    {
        SystemClock.sleep(1500);

        // Guest Login - Guest can view ads without logging In
        onView(withId(R.id.view_pager_mainActivity)).perform(click());
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
        onView(withId(R.id.forgotPasswordBtn)).perform(click());



        // click on reset password without doing anything
        onView(withId(R.id.getSecuriityQuestionBtn)).perform(click());
        SystemClock.sleep(1500);



        // click on reset password without doing anything
        onView(withId(R.id.answeredSecurityQuestionBtn)).perform(click());
        SystemClock.sleep(1500);



        // type text and then click on get security question then click on reset password
        onView(withId(R.id.editText_forgetPassword_userName)).perform(typeText("Bob2"));
        onView(withId(R.id.getSecuriityQuestionBtn)).perform(click());
        pressBack();
        onView(withId(R.id.answeredSecurityQuestionBtn)).perform(click());
        SystemClock.sleep(1500);



        // type security answer
        onView(withId(R.id.editText_forgetPassword_securityAnswer)).perform(typeText("secret"));
        pressBack();
        onView(withId(R.id.answeredSecurityQuestionBtn)).perform(click());
        onView(withHint(" New Password")).perform(typeText("1234"));
        onView(withHint(" Confirm New Password")).perform(typeText("1234"));
        onView(withText("OK")).perform(click());
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

        // Test updating profile information
        onData(anything()).inAdapterView(withId(R.id.profile_list_view)).atPosition(0).perform(click());
        onView(withId(R.id.profileEditBoxName6)).perform(typeText("Crazy 8"));
        onView(withId(R.id.profileEditBoxEmail)).perform(typeText("ceo@google.ca"));
        onView(withId(R.id.profileEditBoxNumber)).perform(typeText("4312342232"));
        onView(withId(R.id.profileEditBoxAnswer)).perform(typeText("jess"));
        pressBack();
        onView(withId(R.id.profileEditBoxPassword)).perform(typeText("password"));
        pressBack();
        onView(withId(R.id.profie_info_save)).perform(click());
        onView(withText("Logout")).perform(click());
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("password"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.profile_list_view)).atPosition(0).perform(click());
        onView(withId(R.id.profileEditBoxName6)).check(matches(withHint("Crazy 8")));
        onView(withId(R.id.profileEditBoxEmail)).check(matches(withHint("ceo@google.ca")));
        onView(withId(R.id.profileEditBoxNumber)).check(matches(withHint("4312342232")));
        onView(withId(R.id.profileEditBoxAnswer)).check(matches(withHint("jess")));
        pressBack();


        // Test view users own ads from profile.
        onData(anything()).inAdapterView(withId(R.id.profile_list_view)).atPosition(1).perform(click());
        SystemClock.sleep(500);
        pressBack();
        onData(anything()).inAdapterView(withId(R.id.profile_list_view)).atPosition(2).perform(click());
        onView(withId(R.id.textView_postAd_adType)).perform(click());
        SystemClock.sleep(100);
        onView(withText("OFFERING")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        onView(withId(R.id.textView_postAd_category)).perform(click());
        SystemClock.sleep(100);
        onView(withText("BOOKS")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        onView(withId(R.id.editText_postAd_title)).perform(typeText("The conjuring"));
        onView(withId(R.id.editText_postAd_description)).perform(typeText("Book for sale!"));
        pressBack();
        onView(withId(R.id.editText_postAd_price)).perform(typeText("10.0"));
        pressBack();
        onView(withId(R.id.button_post_ad)).perform(click());
        pressBack();
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.profile_list_view)).atPosition(1).perform(click());
        onView(withText("The conjuring")).perform(click());
        onView(withId(R.id.textView_viewAdCU_title)).check(matches(withText("The conjuring")));
        onView(withId(R.id.textView_viewAdCU_description)).check(matches(withText("Book for sale!")));



    }

}
