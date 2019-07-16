package com.simplisell;


import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.simplisell.presentation.SplashScreen;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)

@LargeTest
public class SearchFeatureAcceptanceTest {

    @Rule
    public ActivityTestRule<SplashScreen> activityRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void searchFeatureTest()
    {
        // test category search
        SystemClock.sleep(500);
        onView(allOf(withText("Books"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Analysis of Algorithms"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        pressBack();
        onView(allOf(withText("Services & Jobs"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Wanted Tutoring Services"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        pressBack();
        onView(allOf(withText("Electronics"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("iPad 6th Gen"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        pressBack();
        onView(allOf(withText("Events"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Transportation"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("CarPooling Ad Title"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        pressBack();
        onView(allOf(withText("Accommodation"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Other"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tabview_mainActivity)).perform(swipeRight());
        SystemClock.sleep(1000);
        onView(allOf(withText("All"), isDisplayed())).perform(click());
        SystemClock.sleep(500);

        // test filtering by type
        onView(withId(R.id.textView_mainActivity_adType)).perform(click());
        SystemClock.sleep(200);
        onView(withText("OFFERING")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Books"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Services & Jobs"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Electronics"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Events"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Transportation"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Accommodation"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Other"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tabview_mainActivity)).perform(swipeRight());
        SystemClock.sleep(1000);
        onView(allOf(withText("All"), isDisplayed())).perform(click());
        SystemClock.sleep(500);

        onView(withId(R.id.textView_mainActivity_adType)).perform(click());
        SystemClock.sleep(200);
        onView(withText("WANTED")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Books"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Services & Jobs"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Electronics"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Events"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Transportation"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Accommodation"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Other"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tabview_mainActivity)).perform(swipeRight());
        SystemClock.sleep(1000);
        onView(allOf(withText("All"), isDisplayed())).perform(click());
        SystemClock.sleep(500);

        onView(withId(R.id.textView_mainActivity_adType)).perform(click());
        SystemClock.sleep(200);
        onView(withText("All Types")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        SystemClock.sleep(500);

        // test sorting by price
        SystemClock.sleep(1000);
        onView(withId(R.id.button_mainActivity_sortByPrice)).perform(click());
        SystemClock.sleep(1000);
        onView(allOf(withText("Books"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Services & Jobs"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Electronics"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Events"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Transportation"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Accommodation"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(allOf(withText("Other"), isDisplayed())).perform(click());
        SystemClock.sleep(500);
        onView(withId(R.id.tabview_mainActivity)).perform(swipeRight());
        SystemClock.sleep(1000);
        onView(allOf(withText("All"), isDisplayed())).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.button_mainActivity_sortByPrice)).perform(click());
        SystemClock.sleep(1000);

        // combined search test
        SystemClock.sleep(1000);
        onView(withId(R.id.textView_mainActivity_adType)).perform(click());
        SystemClock.sleep(200);
        onView(withText("OFFERING")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        SystemClock.sleep(500);

        onView(withId(R.id.button_mainActivity_sortByPrice)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.button_mainActivity_sortByPrice)).perform(click());
        SystemClock.sleep(1000);

        onView(allOf(withText("Books"), isDisplayed())).perform(click());
        SystemClock.sleep(500);

        onView(withId(R.id.button_mainActivity_sortByPrice)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.button_mainActivity_sortByPrice)).perform(click());
        SystemClock.sleep(1000);

        onView(allOf(withText("All"), isDisplayed())).perform(click());
        SystemClock.sleep(1000);

        onView(withId(R.id.textView_mainActivity_adType)).perform(click());
        SystemClock.sleep(200);
        onView(withText("All Types")).perform(click());
        SystemClock.sleep(200);
        onView(withText("OK")).perform(click());
        SystemClock.sleep(500);
    }
}
