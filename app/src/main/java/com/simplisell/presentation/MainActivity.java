package com.simplisell.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.widget.TextView;
import com.simplisell.R;
import com.simplisell.business.AccessAds;
import com.simplisell.business.AccessUsers;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.objects.User;
import com.simplisell.presentation.homepagetabs.TabFragment;
import com.simplisell.presentation.homepagetabs.TabPagerAdapter;
import com.simplisell.presentation.loginactivity.Login;
import com.simplisell.presentation.postingadactivity.PostAd;
import com.simplisell.presentation.userprofileactivity.UserProfileButton;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;

public class MainActivity extends AppCompatActivity implements UserProfileButton
{
    private final String USERNAME_TEXT = "USER";

    private static String userName = null;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AccessAds accessAds;             // helps  access ads


    private TabFragment tabFragmentAllObj;
    private TabFragment tabFragmentBooksObj;
    private TabFragment tabFragmentTransportationObj;
    private TabFragment tabFragmentServicesJobsObj;
    private TabFragment tabFragmentLivingObj;
    private TabFragment tabFragmentEventsObj;
    private TabFragment tabFragmentElectronicsObj;
    private TabFragment tabFragmentOtherObj;

    private String typeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accessAds = new AccessAds();
        accessAds.removeExpiredAds();

        getCurrentUserName();
        initializeTabFragments();

        tabSetUp();
    }


    private void initializeTabFragments()
    {
        tabFragmentAllObj = new TabFragment(accessAds.getAllAds(), accessAds);
        tabFragmentBooksObj = new TabFragment(accessAds.getAllAdsByCategory(Category.BOOKS), accessAds);
        tabFragmentTransportationObj = new TabFragment(accessAds.getAllAdsByCategory(Category.TRANSPORTATION),
                accessAds);
        tabFragmentServicesJobsObj = new TabFragment(accessAds.getAllAdsByCategory(Category.JOBS_SERVICES), accessAds);
        tabFragmentLivingObj = new TabFragment(accessAds.getAllAdsByCategory(Category.ACCOMMODATION), accessAds);
        tabFragmentEventsObj = new TabFragment(accessAds.getAllAdsByCategory(Category.EVENTS), accessAds);
        tabFragmentElectronicsObj = new TabFragment(accessAds.getAllAdsByCategory(Category.ELECTRONICS), accessAds);
        tabFragmentOtherObj = new TabFragment(accessAds.getAllAdsByCategory(Category.OTHERS), accessAds);
    }

    
    private void tabSetUp()
    {
        tabLayout = findViewById(R.id.tabview_mainActivity);
        viewPager = findViewById(R.id.view_pager_mainActivity);


        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());

        // adding the fragments
        adapter.addFragment(tabFragmentAllObj, "All");
        adapter.addFragment(tabFragmentBooksObj, "Books");
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
        Intent nextActivityIntent;

        if (userName != null)
        {
            nextActivityIntent = new Intent(getApplicationContext(), UserProfileMenu.class);
            nextActivityIntent.putExtra(USERNAME_TEXT, userName);
        }
        else
        {
            // not logged in
            nextActivityIntent = new Intent(getApplicationContext(), Login.class);
        }

        startActivity(nextActivityIntent);
    }


    private void getCurrentUserName()
    {
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(USERNAME_TEXT))
        {

            userName = intentThatStartedThisActivity.getStringExtra(USERNAME_TEXT);


        }
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


    public void filterTypeBtnClick(View view)
    {
        AlertDialog dialog;
        AlertDialog.Builder builder;
        final String[] types = {"All Types", "OFFERING", "WANTED"};

        builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Select the Advertisement Type");

        builder.setSingleChoiceItems(types, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                typeResult = types[which];
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                TextView textView = findViewById(R.id.textView_mainActivity_adType);

                if (typeResult == null)
                {
                    typeResult = types[0];
                }

                textView.setText(typeResult);

                if (typeResult.equals(types[0]))
                {
                    revertTabs();
                }
                else
                {
                    AdType adType = AdType.valueOf(typeResult);

                    filterTabs(adType);
                }

                int position = tabLayout.getSelectedTabPosition();
                tabSetUp();
                viewPager.setCurrentItem(position);
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        dialog = builder.create();
        dialog.show();
    }


    private void filterTabs(AdType adType)
    {
        tabFragmentAllObj.filterByType(adType);
        tabFragmentBooksObj.filterByType(adType);
        tabFragmentTransportationObj.filterByType(adType);
        tabFragmentServicesJobsObj.filterByType(adType);
        tabFragmentLivingObj.filterByType(adType);
        tabFragmentEventsObj.filterByType(adType);
        tabFragmentElectronicsObj.filterByType(adType);
        tabFragmentOtherObj.filterByType(adType);
    }


    private void revertTabs()
    {
        tabFragmentAllObj.revertAds();
        tabFragmentBooksObj.revertAds();
        tabFragmentTransportationObj.revertAds();
        tabFragmentServicesJobsObj.revertAds();
        tabFragmentLivingObj.revertAds();
        tabFragmentEventsObj.revertAds();
        tabFragmentElectronicsObj.revertAds();
        tabFragmentOtherObj.revertAds();
    }


    public void postAdBtnClick(View view)
    {
        if (Login.isLoggedIn())
        {
            Intent postAd = new Intent(getApplicationContext(), PostAd.class);
            postAd.putExtra(USERNAME_TEXT, userName);
            startActivity(postAd);
        }
        else
        {
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
    }

    public static void logout()
    {
        userName = null;
    }
}
