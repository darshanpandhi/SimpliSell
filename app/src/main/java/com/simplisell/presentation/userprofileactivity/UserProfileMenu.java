package com.simplisell.presentation.userprofileactivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simplisell.R;
import com.simplisell.presentation.loginactivity.Login;
import com.simplisell.presentation.MainActivity;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;

public class UserProfileMenu extends AppCompatActivity
{

    private final String USERNAME_TEXT = "USER";

    private static String userName = null;
    private static ListView listView;
    private static String[] titles = {"My Profile", "My Ads", "Logout"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile_menu);

        userName = getIntent().getStringExtra(USERNAME_TEXT);
        initialize();
    }


    public void initialize()
    {
        listView = findViewById(R.id.profile_list_view);

        ProfileListViewAdapter profileListViewAdapter = new ProfileListViewAdapter(this, titles);

        listView.setAdapter(profileListViewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (position == 0)
                {
                    view.setBackgroundColor(Color.RED);
                    Intent intent = new Intent(getApplicationContext(), ProfileInformation.class);
                    intent.putExtra(USERNAME_TEXT, userName);
                    startActivity(intent);
                }
                else
                {
                    if (position == 1)
                    {
                        view.setBackgroundColor(Color.RED);
                        Intent intent = new Intent(getApplicationContext(), UsersOwnAds.class);
                        intent.putExtra(USERNAME_TEXT, userName);
                        startActivity(intent);
                    }
                    else
                    {
                        if (position == 2)
                        {
                            view.setBackgroundColor(Color.RED);
                            logout();
                        }
                    }
                }
            }
        });
    }


    public void logout()
    {
        Login.logOut();
        userName = null;
        RecyclerViewAdapter.logOut();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
