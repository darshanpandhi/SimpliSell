package com.simplisell.presentation;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.simplisell.R;
import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;

public class MainActivity extends AppCompatActivity {

    private final String USERNAME_TEXT="USER";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AccessUsers accessUsers;      // helps  access users
    private ImageButton profileBtn;

    private static User currUser=null;
    private static String userName=null;
    private static boolean isSortedAscending=false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accessUsers=new AccessUsers();

        if(userName==null)  // if there is no logged in user
        {

            try
            {

                userName = getIntent().getStringExtra(USERNAME_TEXT);   // get the username to see if user was logged in.

                if(userName!=null)
                {

                    currUser=accessUsers.getUser(userName);
                }

            } catch (Exception e) {

                userName = null;
                currUser=null;
            }

        }


        tabLayout = findViewById(R.id.tabview_mainActivity);
        viewPager = findViewById(R.id.view_pager_mainActivity);
        profileBtn =findViewById(R.id.imageButton_mainActivty_accountButton);

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());

        // adding the fragments
        adapter.addFragment(new TabFragmentAll(), "All");
        adapter.addFragment(new TabFragmentBooks(), "Book");
        adapter.addFragment(new TabFragmentServicesJobs(), "Services & Jobs");
        adapter.addFragment(new TabFragmentElectronics(), "Electronics");
        adapter.addFragment(new TabFragmentEvents(), "Events");
        adapter.addFragment(new TabFragmentTransportation(), "Transportation");
        adapter.addFragment(new TabFragmentLiving(), "Accomodation");
        adapter.addFragment(new TabFragmentOther(), "Other");


        // adapter setup

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }


    static void logOutUser(){   // logs out user
        currUser=null;
        userName=null;
    }

    @Override
    public void onBackPressed() {   // if anytime the back is pressed. Go to app home

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }


    public void accountBtnClick(View view) {

        if(userName == null)
        {   // not logged in

            startActivity(new Intent(getApplicationContext() , Login.class));

        }
        else   // already logged in
        {

            //TODO  go to user profile and logout button
        }
    }


    public void sortBtnClick(View view) {


    }
}