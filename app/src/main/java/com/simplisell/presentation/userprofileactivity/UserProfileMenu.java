package com.simplisell.presentation.userprofileactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simplisell.R;
import com.simplisell.application.Services;
import com.simplisell.business.AccessUsers;
import com.simplisell.objects.User;
import com.simplisell.persistence.UserPersistence;
import com.simplisell.presentation.loginactivity.Login;
import com.simplisell.presentation.MainActivity;
import com.simplisell.presentation.postingadactivity.PostAd;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;
import com.simplisell.presentation.useradminactivity.ReportedAds;

public class UserProfileMenu extends AppCompatActivity
{

    private final String USERNAME_TEXT = "USER";
    private User user;
    private AccessUsers accessUsers;

    private static String userName = null;
    private static ListView listView;
    private static String[] titlesNotAdmin = {"My Profile", "My Ads", "Post an Ad", "Logout"};
    private static String[] titlesIsAdmin = {"My Profile", "My Ads","Post an Ad","Reported Ads", "Logout"};



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile_menu);

        userName = getIntent().getStringExtra(USERNAME_TEXT);
        accessUsers = new AccessUsers();
        user = accessUsers.getUser(userName);

        initialize();
    }


    public void initialize()
    {
        listView = findViewById(R.id.profile_list_view);

        ProfileListViewAdapter profileListViewAdapter = null;


        if(user.isAdmin())  // if user is an admin
        {
            profileListViewAdapter = new ProfileListViewAdapter(this, titlesIsAdmin);
        }
        else
        {
            profileListViewAdapter = new ProfileListViewAdapter(this, titlesNotAdmin);

        }

        listView.setAdapter(profileListViewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                view.setBackgroundColor(Color.RED);
                if (position == 0)
                {
                    Intent postAd = new Intent(getApplicationContext(), ProfileInformation.class);
                    postAd.putExtra(USERNAME_TEXT, userName);
                    startActivity(postAd);
                } else if (position == 1)
                {
                    Intent postAd = new Intent(getApplicationContext(), UsersOwnAds.class);
                    postAd.putExtra(USERNAME_TEXT, userName);
                    startActivity(postAd);
                } else if (position == 2)
                {
                    Intent postAd = new Intent(getApplicationContext(), PostAd.class);
                    postAd.putExtra(USERNAME_TEXT, userName);
                    startActivity(postAd);
                }
                if (user.isAdmin())
                {
                    if (position == 3)
                    {
                        Intent intent = new Intent(getApplicationContext(), ReportedAds.class);
                        intent.putExtra(USERNAME_TEXT, userName);
                        startActivity(intent);
                    } else if (position == 4)
                    {
                        logout();
                    }
                } else
                {
                    if (position == 3)
                    {
                        logout();
                    }

                }
            }
        });
    }


    public void logout()
    {
        Login.logOut();
        userName = null;
        MainActivity.logout();
        RecyclerViewAdapter.logOut();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
