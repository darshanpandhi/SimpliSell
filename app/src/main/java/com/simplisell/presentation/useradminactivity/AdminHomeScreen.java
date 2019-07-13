package com.simplisell.presentation.useradminactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.simplisell.R;
import android.content.Intent;
import com.simplisell.business.AccessAds;
import com.simplisell.objects.Ad;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;
import com.simplisell.presentation.userprofileactivity.UserProfileButton;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;
import com.simplisell.presentation.loginactivity.Login;

import java.util.List;

public class AdminHomeScreen extends AppCompatActivity implements UserProfileButton
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_screen);

        AccessAds accessAds = new AccessAds();

        List<Ad> reportedAds = accessAds.getReportedAds();


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.admin_reportedAds_recycle_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, reportedAds);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onBackPressed()
    {
        // stay on the admin home screen whenever the back button is pressed
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
}
