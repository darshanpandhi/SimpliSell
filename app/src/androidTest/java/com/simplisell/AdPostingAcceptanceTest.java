package com.simplisell;


import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.simplisell.presentation.SplashScreen;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class AdPostingAcceptanceTest {


    @Rule
    public ActivityTestRule<SplashScreen> activityRule = new ActivityTestRule<>(SplashScreen.class);

    @Before
    public void login()
    {
        // sign in with user Bob
        onView(withId(R.id.button_mainActivity_postAnAd)).perform(click());
        onView(withId(R.id.editText_login_enterUserName)).perform(typeText("Bob"));
        onView(withId(R.id.editText_login_enterPassword)).perform(clearText());
        onView(withId(R.id.editText_login_enterPassword)).perform(typeText("123456"));
        onView(withId(R.id.button_login_loginButton)).perform(click());
        SystemClock.sleep(1000);
    }

    @Test
    public void postAdTest()
    {
        // Bob tries to post an ad
        onView(withId(R.id.button_mainActivity_postAnAd)).perform(click());
        onView(withId(R.id.textView_postAd_adType)).perform(click());
        SystemClock.sleep(200);
        onView(withText("OFFERING")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        pressBack();
        onView(withId(R.id.button_post_ad)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.textView_postAd_category)).perform(click());
        SystemClock.sleep(200);
        onView(withText("BOOKS")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        onView(withId(R.id.button_post_ad)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.editText_postAd_title)).perform(typeText("MATH1500"));
        pressBack();
        onView(withId(R.id.button_post_ad)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.editText_postAd_description)).perform(typeText("Selling my MATH1500 textbook."));
        pressBack();
        onView(withId(R.id.button_post_ad)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.editText_postAd_price)).perform(typeText("."));
        pressBack();
        onView(withId(R.id.button_post_ad)).perform(click());
        SystemClock.sleep(1500);
        onView(withId(R.id.editText_postAd_price)).perform(clearText());
        onView(withId(R.id.editText_postAd_price)).perform(typeText("50"));
        pressBack();
        onView(withId(R.id.button_post_ad)).perform(click());
        SystemClock.sleep(1500);


        // start editing ad
        onView(withId(R.id.button_viewAdCU_edit)).perform(click());

        // edit category
        onView(withId(R.id.textView_editAd_category)).perform(click());
        onView(withText("ELECTRONICS")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        onView(withId((R.id.textView_editAd_category))).check(matches(withText("ELECTRONICS")));
        onView(withId(R.id.textView_editAd_category)).perform(click());
        onView(withText("BOOKS")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());

        // edit title
        onView(withId(R.id.editText_editAd_title)).perform(typeText(" And MATH1700 Textbooks"));

        // edit description
        onView(withId(R.id.editText_editAd_description)).perform(clearText());
        onView(withId(R.id.editText_editAd_description)).perform(typeText("Selling my MATH1500 and MATH1700 textbooks."));
        pressBack();

        // edit price
        onView(withId(R.id.editText_editAd_price)).perform(clearText());
        onView(withId(R.id.editText_editAd_price)).perform(typeText("100"));
        pressBack();

        // update ad
        onView(withId(R.id.button_editAd_save)).perform(click());

        pressBack();

        // check if ad is updated correctly
        onView(allOf(withText("MATH1500 And MATH1700 Textbooks"), isDisplayed())).perform(click());
        onView(withId(R.id.textView_viewAdCU_title)).check(matches(withText("MATH1500 And MATH1700 Textbooks")));
        onView(withId(R.id.textView_viewAdCU_description)).check(matches(withText("Selling my MATH1500 and MATH1700 textbooks.")));
        onView(withId(R.id.textView_viewAdCU_price)).check(matches(withText("$100.0")));


        // repost
        onView(withId(R.id.button_viewAdCU_repost)).perform(click());
        SystemClock.sleep(2500);

        // delete ad
        onView(allOf(withText("MATH1500 And MATH1700 Textbooks"), isDisplayed())).perform(click());
        onView(withId(R.id.button_viewAdCU_delete)).perform(click());
        SystemClock.sleep(1500);

        // view individual ad
        onView(allOf(withText("iPad 6th Gen"), isDisplayed())).perform(click());
        SystemClock.sleep(2000);
        pressBack();
        onView(allOf(withText("CarPooling Ad Title"), isDisplayed())).perform(click());
        SystemClock.sleep(2000);
        pressBack();
        onView(allOf(withText("Analysis of Algorithms"), isDisplayed())).perform(click());
        SystemClock.sleep(2000);
        pressBack();
    }
}
