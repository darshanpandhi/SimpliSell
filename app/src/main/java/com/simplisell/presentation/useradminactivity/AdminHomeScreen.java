package com.simplisell.presentation.useradminactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import com.simplisell.R;
import android.content.Intent;
import com.simplisell.presentation.userprofileactivity.UserProfileButton;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;
import com.simplisell.presentation.loginactivity.Login;

public class AdminHomeScreen extends AppCompatActivity implements UserProfileButton
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_screen);
    }

    public void viewReportedUsersBtnClick(View view)
    {

    }

    public void viewReportedAdsBtnClick(View view)
    {

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
