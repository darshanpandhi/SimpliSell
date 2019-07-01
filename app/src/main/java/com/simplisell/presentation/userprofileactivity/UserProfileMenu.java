package com.simplisell.presentation.userprofileactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simplisell.R;
import com.simplisell.objects.User;
import com.simplisell.presentation.loginactivity.Login;
import com.simplisell.presentation.MainActivity;
import com.simplisell.presentation.postingadactivity.PostAd;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;

public class UserProfileMenu extends AppCompatActivity
{
    private static String userName = null;
    private static User currUser = null;
    private ListView listView;
    private static String[] titles = {"Post an Ad", "Logout"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (Login.isLoggedIn())
        {
            setContentView(R.layout.activity_user_profile_menu);
            initialize();
        }
        else
        {
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
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
                    Intent postAd = new Intent(getApplicationContext(), PostAd.class);
                    postAd.putExtra(Intent.EXTRA_TEXT, userName);
                    startActivity(postAd);
                }
                else
                {
                    if (position == 1)
                    {
                        logout();
                    }
                }
            }
        });
    }


    public void accountBtnClick(View view)
    {
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


    public void logout()
    {
        MainActivity.logOutUser();
        userName = null;
        RecyclerViewAdapter.logOut();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
