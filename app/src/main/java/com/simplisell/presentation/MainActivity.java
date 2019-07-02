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
import com.simplisell.business.Search;
import com.simplisell.objects.Category;
import com.simplisell.objects.EncoderDecoder;
import com.simplisell.presentation.homepagetabs.TabFragment;
import com.simplisell.presentation.homepagetabs.TabPagerAdapter;
import com.simplisell.presentation.loginactivity.Login;
import com.simplisell.presentation.postingadactivity.PostAd;
import com.simplisell.presentation.userprofileactivity.UserProfileButton;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;

public class MainActivity extends AppCompatActivity implements UserProfileButton
{
    private static String userName = null;

    private TabLayout tabLayout;
    private ViewPager viewPager;
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
        Search search = new Search();

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

//        if (Login.isLoggedIn())
//        {
//            String profilePhoto = currUser.getProfilePhoto();
//
//            if (profilePhoto != null)
//            {
//                Bitmap photo = EncoderDecoder.stringToBitMap(profilePhoto);
//
//                Bitmap displayProfile = Bitmap.createScaledBitmap(photo, (int) (photo.getWidth() * 0.7),
//                        (int) (photo.getHeight() * 0.7), true);
//
//                profileBtn.setImageBitmap(displayProfile);
//            }
//        }
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
        if (Login.isLoggedIn())
        {
            Intent postAd = new Intent(getApplicationContext(), PostAd.class);
            postAd.putExtra(Intent.EXTRA_TEXT, userName);
            startActivity(postAd);
        }
        else
        {
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
    }
}
