package com.simplisell.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.simplisell.R;
import com.simplisell.application.Main;
import com.simplisell.business.AccessAds;
import com.simplisell.business.AccessUsers;
import com.simplisell.business.Search;
import com.simplisell.objects.Ad;
import com.simplisell.objects.Category;
import com.simplisell.objects.EncoderDecoder;
import com.simplisell.objects.User;
import com.simplisell.presentation.HomePageTabs.TabFragment;
import com.simplisell.presentation.HomePageTabs.TabPagerAdapter;
import com.simplisell.presentation.LoginActivity.Login;
import com.simplisell.presentation.PostingAdActivity.PostAd;
import com.simplisell.presentation.UserProfileActivity.UserProfileMenu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        copyDatabaseToDevice();
        search = new Search();

        tabFragmentAllObj = new TabFragment(search.getAllAds());
        tabFragmentBooksObj = new TabFragment(search.getAllAdsByCategory(Category.BOOKS));
        tabFragmentTransportationObj = new TabFragment(search.getAllAdsByCategory(Category.TRANSPORTATION));
        tabFragmentServicesJobsObj = new TabFragment(search.getAllAdsByCategory(Category.JOBS_SERVICES));
        tabFragmentLivingObj = new TabFragment(search.getAllAdsByCategory(Category.ACCOMMODATION));
        tabFragmentEventsObj = new TabFragment(search.getAllAdsByCategory(Category.EVENTS));
        tabFragmentElectronicsObj = new TabFragment(search.getAllAdsByCategory(Category.ELECTRONICS));
        tabFragmentOtherObj = new TabFragment(search.getAllAdsByCategory(Category.OTHERS));

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
        } else
        {
            currUser = accessUsers.getUser(userName);   //Update to show profile image
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

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {
            //load in all asset file
            assetNames = assetManager.list(DB_PATH);

            if (assetNames != null) {
                for (int i = 0; i < assetNames.length; i++) {
                    assetNames[i] = DB_PATH + "/" + assetNames[i];
                }

                copyAssetsToDirectory(assetNames, dataDirectory);

                Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());
            }
        } catch (final IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }


    private void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            //check if database file already exit?
            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }


}