package com.simplisell.presentation.useradminactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simplisell.R;
import com.simplisell.business.Search;
import com.simplisell.objects.User;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;

import java.util.List;

public class ReportedUsers extends AppCompatActivity
{

    private static final String USERNAME_TEXT="USER";

    private List<User> reportedUsers;
    private Search adsSearch;
    private RecyclerView recyclerView;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_own_ads);
        userName = getIntent().getStringExtra(USERNAME_TEXT);


        adsSearch = new Search();
        reportedUsers = adsSearch.getReportedUsers();

        recyclerView = (RecyclerView) findViewById(R.id.profile_usersAds_recycle_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerViewAdapter.setMyUser(reportedUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void onBackPressed()
    {   // if anytime the back is pressed. Go back

        finish();
        Intent i = new Intent(this, UserProfileMenu.class);
        i.putExtra(USERNAME_TEXT, userName);
        startActivity(i);
    }


}