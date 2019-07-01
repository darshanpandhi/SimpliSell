package com.simplisell.presentation;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.simplisell.R;
import com.simplisell.presentation.homepagetabs.TabFragmentAll;
import com.simplisell.presentation.homepagetabs.TabFragmentBooks;
import com.simplisell.presentation.homepagetabs.TabFragmentElectronics;
import com.simplisell.presentation.homepagetabs.TabFragmentEvents;
import com.simplisell.presentation.homepagetabs.TabFragmentLiving;
import com.simplisell.presentation.homepagetabs.TabFragmentOther;
import com.simplisell.presentation.homepagetabs.TabFragmentServicesJobs;
import com.simplisell.presentation.homepagetabs.TabFragmentTransportation;
import com.simplisell.presentation.homepagetabs.TabPagerAdapter;
import com.simplisell.presentation.loginactivity.Login;
import com.simplisell.presentation.postingadactivity.PostAd;
import com.simplisell.presentation.userprofileactivity.UserProfileButton;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;

public class MainActivity extends AppCompatActivity implements UserProfileButton
{
    private static String userName = null;
    private static boolean isSortedAscending = false;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton profileBtn;
    private TabFragmentAll tabFragmentAllObj;
    private TabFragmentBooks tabFragmentBooksObj;
    private TabFragmentTransportation tabFragmentTransportationObj;
    private TabFragmentServicesJobs tabFragmentServicesJobsObj;
    private TabFragmentLiving tabFragmentLivingObj;
    private TabFragmentEvents tabFragmentEventsObj;
    private TabFragmentElectronics tabFragmentElectronicsObj;
    private TabFragmentOther tabFragmentOtherObj;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        tabFragmentAllObj = new TabFragmentAll();
        tabFragmentBooksObj = new TabFragmentBooks();
        tabFragmentTransportationObj = new TabFragmentTransportation();
        tabFragmentServicesJobsObj = new TabFragmentServicesJobs();
        tabFragmentLivingObj = new TabFragmentLiving();
        tabFragmentEventsObj = new TabFragmentEvents();
        tabFragmentElectronicsObj = new TabFragmentElectronics();
        tabFragmentOtherObj = new TabFragmentOther();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabSetUp();
    }


    private void tabSetUp()
    {
        tabLayout = findViewById(R.id.tabview_mainActivity);
        viewPager = findViewById(R.id.view_pager_mainActivity);
        profileBtn = findViewById(R.id.imageButton_mainActivty_accountButton);

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());

        // adding the fragments
        adapter.addFragment(tabFragmentAllObj, "All");
        adapter.addFragment(tabFragmentBooksObj, "Book");
        adapter.addFragment(tabFragmentServicesJobsObj, "Services & Jobs");
        adapter.addFragment(tabFragmentElectronicsObj, "Electronics");
        adapter.addFragment(tabFragmentEventsObj, "Events");
        adapter.addFragment(tabFragmentTransportationObj, "Transportation");
        adapter.addFragment(tabFragmentLivingObj, "Accommodation");
        adapter.addFragment(tabFragmentOtherObj, "Other");


        // adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    public static void logOutUser()
    {   // logs out user
        userName = null;
    }


    @Override
    public void onBackPressed()
    {
        // if anytime the back is pressed. Go to app home
        homeScreen();
    }

    private void homeScreen()
    {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }


    public void accountBtnClick(View view)
    {
        Intent intent;

        if (Login.isLoggedIn())
        {
            // already logged in
            intent = new Intent(getApplicationContext(), UserProfileMenu.class);
        }
        else
        {
            // not logged in
            intent = new Intent(getApplicationContext(), Login.class);
        }

        startActivity(intent);
    }


    public void sortBtnClick(View view)
    {

        int position = tabLayout.getSelectedTabPosition();   // get position of current tab layout

        tabFragmentAllObj.sort();
        tabFragmentBooksObj.sort();
        tabFragmentTransportationObj.sort();
        tabFragmentServicesJobsObj.sort();
        tabFragmentLivingObj.sort();
        tabFragmentEventsObj.sort();
        tabFragmentElectronicsObj.sort();
        tabFragmentOtherObj.sort();

        tabSetUp();                 // set up all tabs again

        viewPager.setCurrentItem(position); // set it to the position user wanted
    }


    public void postAdBtnClick(View view)
    {
        if (userName == null)
        {
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
        else
        {
            Intent postAd = new Intent(getApplicationContext(), PostAd.class);
            postAd.putExtra(Intent.EXTRA_TEXT, userName);
            startActivity(postAd);
        }
    }
}