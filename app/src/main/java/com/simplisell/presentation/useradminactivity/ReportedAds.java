package com.simplisell.presentation.useradminactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.simplisell.R;
import com.simplisell.business.AccessAds;
import com.simplisell.objects.Ad;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;

import java.util.List;

public class ReportedAds extends AppCompatActivity
{

    private static final String USERNAME_TEXT="USER";

    private List<Ad> reportedAds;
    private RecyclerView recyclerView;
    private String userName;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_own_ads);
        userName = getIntent().getStringExtra(USERNAME_TEXT);

        AccessAds accessAds = new AccessAds();
        reportedAds = accessAds.getReportedAds();


        recyclerView = (RecyclerView) findViewById(R.id.profile_usersAds_recycle_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, reportedAds);
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
