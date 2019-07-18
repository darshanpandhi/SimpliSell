package com.simplisell.acceptancetests;

import android.os.SystemClock;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.simplisell.R;
import com.simplisell.presentation.SplashScreen;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)

/*
    This acceptance test covers both Admin Control feature and Report Ads feature
 */
@LargeTest
public class AdminAndReportFeaturesAcceptanceTest
{

    @Rule
    public ActivityTestRule<SplashScreen> activityRule = new ActivityTestRule<>(SplashScreen.class);


    @Test
    public void adminReportTest()
    {
        // login in with Bob
        SystemClock.sleep(2500);
        onView(ViewMatchers.withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob"));
        onView(withId(R.id.editText_login_enterPassword)).perform(clearText());
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("123456"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1000);

        // Bob posts an ad to be reported
        onView(withId(R.id.button_mainActivity_postAnAd)).perform(click());
        onView(withId(R.id.textView_postAd_adType)).perform(click());
        SystemClock.sleep(1500);
        onView(withText("OFFERING")).perform(click());
        SystemClock.sleep(1500);
        onView(withText("OK")).perform(click());
        onView(withId(R.id.textView_postAd_category)).perform(click());
        SystemClock.sleep(1500);
        onView(withText("OTHERS")).perform(click());
        SystemClock.sleep(1500);
        onView(withText("OK")).perform(click());
        onView(withId(R.id.editText_postAd_title)).perform(typeText("Inappropriate ad title"));
        onView(withId(R.id.editText_postAd_description)).perform(typeText("Inappropriate ad description."));
        pressBack();
        onView(withId(R.id.editText_postAd_price)).perform(typeText("99999"));
        pressBack();
        onView(withId(R.id.button_post_ad)).perform(click());
        SystemClock.sleep(1500);
        pressBack();

        // Bob logout
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        onView(withText("Logout")).perform(click());
        SystemClock.sleep(1500);

        // A guest reports the inappropriate ad 3 times
        onView(withId(R.id.view_pager_mainActivity)).perform(swipeUp());
        onView(allOf(withText("Inappropriate ad title"), isDisplayed())).perform(click());
        onView(withId(R.id.button_viewAdOU_reportAd)).perform(click());
        SystemClock.sleep(1500);

        onView(withId(R.id.view_pager_mainActivity)).perform(swipeUp());
        onView(allOf(withText("Inappropriate ad title"), isDisplayed())).perform(click());
        onView(withId(R.id.button_viewAdOU_reportAd)).perform(click());
        SystemClock.sleep(1500);

        onView(withId(R.id.view_pager_mainActivity)).perform(swipeUp());
        onView(allOf(withText("Inappropriate ad title"), isDisplayed())).perform(click());
        onView(withId(R.id.button_viewAdOU_reportAd)).perform(click());
        SystemClock.sleep(1500);


        // admin login
        onView(withId(R.id.button_mainActivity_postAnAd)).perform(click());
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Ronak"));
        onView(withId(R.id.editText_login_enterPassword)).perform(clearText());
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("admin"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1000);

        // admin view reported ads and delete the inappropriate ad
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        SystemClock.sleep(1500);
        onView(withText("Reported Ads")).perform(click());
        SystemClock.sleep(1500);
        onView(withText("Inappropriate ad title")).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.button_viewAdCU_delete)).perform(click());

        // admin logout
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        SystemClock.sleep(1500);
        onView(withText("Logout")).perform(click());

        // Bob login again to see his ad
        onView(withId(R.id.button_mainActivity_postAnAd)).perform(click());
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob"));
        onView(withId(R.id.editText_login_enterPassword)).perform(clearText());
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("123456"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.view_pager_mainActivity)).perform(swipeUp());
        SystemClock.sleep(1000);

        // Bob cannot find his ad and logout
        SystemClock.sleep(1000);
        onView(withId(R.id.imageButton_mainActivity_accountButton)).perform(click());
        SystemClock.sleep(1000);
        onView(withText("My Ads")).perform(click());
        SystemClock.sleep(1000);
        pressBack();
        onView(withText("Logout")).perform(click());
    }
}
