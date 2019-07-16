package com.simplisell;


import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.simplisell.application.Services;
import com.simplisell.objects.User;
import com.simplisell.persistence.AdPersistence;
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

@RunWith(AndroidJUnit4.class)

@LargeTest
public class UserProfileFeatureAcceptanceTest
{

    private UserPersistence userPersistence;
    private AdPersistence adPersistence;


    @Rule
    public ActivityTestRule<SplashScreen> activityRule = new ActivityTestRule<>(SplashScreen.class);

    @Before
    public void setupDatabase()
    {

        userPersistence = Services.getUserPersistence();

        userPersistence.insertUser(new User("","Bob2","1111111111","Whats your mothers maiden name?","No Idea",null,null,false));

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
    public void ProfileFeatureTest()
    {
        SystemClock.sleep(1500);


        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("1111111111"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());


        // Test updating profile information
        onData(anything()).inAdapterView(withId(R.id.profile_list_view)).atPosition(0).perform(click());
        onView(withId(R.id.profileEditBoxName6)).perform(typeText("Crazy 8"));
        onView(withId(R.id.profileEditBoxEmail)).perform(typeText("ceo@google.ca"));
        onView(withId(R.id.profileEditBoxNumber)).perform(typeText("4312342232"));
        pressBack();
        onView(withId(R.id.profileEditBoxAnswer)).perform(typeText("jess"));
        pressBack();
        onView(withId(R.id.profileEditBoxPassword)).perform(typeText("password"));
        pressBack();
        onView(withId(R.id.profie_info_save)).perform(click());
        onView(withText("Logout")).perform(click());
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());

        // Test login with old password
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob2"));
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("1111111111"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1500);


        // Test login with new password
        onView(withId(R.id.editText_login_enterPassword)).perform(clearText());
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
        onView(withId(R.id.button_viewAdCU_delete)).perform(click());
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onView(withText("Logout")).perform(click());


    }

}
