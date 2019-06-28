package com.simplisell.presentation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.business.Search;
import com.simplisell.objects.Category;
import com.simplisell.objects.EncoderDecoder;
import com.simplisell.objects.User;
import com.simplisell.presentation.HomePageTabs.TabFragment;
import com.simplisell.presentation.HomePageTabs.TabPagerAdapter;
import com.simplisell.presentation.LoginActivity.Login;
import com.simplisell.presentation.PostingAdActivity.PostAd;
import com.simplisell.presentation.UserProfileActivity.UserProfileMenu;

public class MainActivity extends AppCompatActivity
{

    private static User currUser = null;
    private static String userName = null;
    private static boolean isSortedAscending = false;

    private final String USERNAME_TEXT = "USER";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AccessUsers accessUsers;      // helps  access users
    private Search search;
    private ImageButton profileBtn;
    private TabFragment tabFragmentAllObj;
    private TabFragment tabFragmentBooksObj;
    private TabFragment tabFragmentTransportationObj;
    private TabFragment tabFragmentServicesJobsObj;
    private TabFragment tabFragmentLivingObj;
    private TabFragment tabFragmentEventsObj;
    private TabFragment tabFragmentElectronicsObj;
    private TabFragment tabFragmentOtherObj;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        search = new Search();

        tabFragmentAllObj = new TabFragment(search.getAllAds());
        tabFragmentBooksObj = new TabFragment(search.getAllAdsByCategory(Category.BOOKS));
        tabFragmentTransportationObj = new TabFragment(search.getAllAdsByCategory(Category.TRANSPORTATION));
        tabFragmentServicesJobsObj = new TabFragment(search.getAllAdsByCategory(Category.JOBS_SERVICES));
        tabFragmentLivingObj = new TabFragment(search.getAllAdsByCategory(Category.ACCOMMODATION));
        tabFragmentEventsObj = new TabFragment(search.getAllAdsByCategory(Category.EVENTS));
        tabFragmentElectronicsObj = new TabFragment(search.getAllAdsByCategory(Category.ELECTRONICS));
        tabFragmentOtherObj = new TabFragment(search.getAllAdsByCategory(Category.OTHERS));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accessUsers = new AccessUsers();
        profileBtn = (ImageButton) findViewById(R.id.imageButton_mainActivty_accountButton);


        if (userName == null)  // if there is no logged in user
        {
            try
            {

                userName = getIntent().getStringExtra(USERNAME_TEXT);   // get the username to see if user was logged in.

                if (userName != null)
                {

                    currUser = accessUsers.getUser(userName);
                }
            } catch (Exception e)
            {

                userName = null;
                currUser = null;
            }
        }


        if(currUser != null) {

            String profilePhoto = currUser.getProfilePhoto();

            if (profilePhoto != null) {

                Bitmap photo = EncoderDecoder.stringToBitMap(profilePhoto);

                Bitmap displayProfile = Bitmap.createScaledBitmap(photo,(int)(photo.getWidth()*0.7),(int)(photo.getHeight()*0.7),true);

                profileBtn.setImageBitmap(displayProfile);
            }
        }

        tabSetUp();
    }


    private void tabSetUp()
    {

        tabLayout = findViewById(R.id.tabview_mainActivity);
        viewPager = findViewById(R.id.view_pager_mainActivity);


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
}