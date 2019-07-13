package com.simplisell.presentation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.simplisell.R;
import com.simplisell.application.Main;
import com.simplisell.business.AccessUsers;
import com.simplisell.business.AccessAds;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.business.EncoderDecoder;
import com.simplisell.objects.User;
import com.simplisell.presentation.homepagetabs.TabFragment;
import com.simplisell.presentation.homepagetabs.TabPagerAdapter;
import com.simplisell.presentation.loginactivity.Login;
import com.simplisell.presentation.postingadactivity.PostAd;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity
{

    private static User currUser = null;
    private static String userName = null;

    private final String USERNAME_TEXT = "USER";

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private AccessUsers accessUsers;      // helps  access users
    private AccessAds search;             // helps  access ads

    private ImageButton profileBtn;

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

        search = new AccessAds();
        initializeTabFragments();

        accessUsers = new AccessUsers();

        getLoggedInUser();

        profileBtn = (ImageButton) findViewById(R.id.imageButton_mainActivity_accountButton);
        displayProfilePhoto();

        tabSetUp();
    }





    void initializeTabFragments()
    {
        tabFragmentAllObj = new TabFragment(search.getAllAds(), search);
        tabFragmentBooksObj = new TabFragment(search.getAllAdsByCategory(Category.BOOKS), search);
        tabFragmentTransportationObj = new TabFragment(search.getAllAdsByCategory(Category.TRANSPORTATION), search);
        tabFragmentServicesJobsObj = new TabFragment(search.getAllAdsByCategory(Category.JOBS_SERVICES), search);
        tabFragmentLivingObj = new TabFragment(search.getAllAdsByCategory(Category.ACCOMMODATION), search);
        tabFragmentEventsObj = new TabFragment(search.getAllAdsByCategory(Category.EVENTS), search);
        tabFragmentElectronicsObj = new TabFragment(search.getAllAdsByCategory(Category.ELECTRONICS), search);
        tabFragmentOtherObj = new TabFragment(search.getAllAdsByCategory(Category.OTHERS), search);
    }


    private void getLoggedInUser()
    {
        if (userName == null)  // if there is no logged in user
        {
            try
            {

                userName = getIntent().getStringExtra(USERNAME_TEXT);   // get the username to see if user was logged
                // in.

                if (userName != null)
                {

                    currUser = accessUsers.getUser(userName);
                }
            }
            catch (Exception e)
            {

                userName = null;
                currUser = null;
            }
        }
        else
        {
            currUser = accessUsers.getUser(userName);   //Update to show profile image
        }
    }


    private void displayProfilePhoto()
    {
        if (currUser != null)
        {
            String profilePhoto = currUser.getProfilePhoto();

            if (profilePhoto != null)
            {

                Bitmap photo = EncoderDecoder.stringToBitMap(profilePhoto);

                Bitmap displayProfile = Bitmap.createScaledBitmap(photo, (int) (photo.getWidth() * 0.7),
                        (int) (photo.getHeight() * 0.7), true);

                profileBtn.setImageBitmap(displayProfile);
            }
        }
    }


    public static void logOutUser()
    {   // logs out user
        currUser = null;
        userName = null;
    }


    @Override
    public void onBackPressed()
    {   // if anytime the back is pressed. Go to app home

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }


    public void accountBtnClick(View view)
    {

        if (userName == null)
        {   // not logged in

            startActivity(new Intent(getApplicationContext(), Login.class));
        }
        else   // already logged in
        {
            finish();
            Intent intent = new Intent(getApplicationContext(), UserProfileMenu.class);
            intent.putExtra(USERNAME_TEXT, userName);
            startActivity(intent);
        }
    }


    public void selectTypeBtnClick(View view)
    {
        search = new AccessAds();
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
            finish();
            Intent postAd = new Intent(getApplicationContext(), PostAd.class);
            postAd.putExtra(USERNAME_TEXT, userName);
            startActivity(postAd);
        }
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




}