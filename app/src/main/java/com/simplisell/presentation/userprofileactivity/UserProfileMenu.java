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
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_profile_menu);

        initialize();
    }


    public void initialize()
    {
        ListView listView = findViewById(R.id.profile_list_view);
        String[] titles = {"Logout"};

        ProfileListViewAdapter profileListViewAdapter = new ProfileListViewAdapter(this, titles);

        listView.setAdapter(profileListViewAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (position == 0)
                {
                    logout();
                }
            }
        });
    }


    private void logout()
    {
        Login.logOut();
        RecyclerViewAdapter.logOut();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
