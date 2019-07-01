package com.simplisell.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.simplisell.R;
import android.view.View;
import android.content.Intent;
import com.simplisell.presentation.loginactivity.Login;
import com.simplisell.presentation.userprofileactivity.UserProfileMenu;

public class user_profile_button extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_button);
    }

    public void accountBtnClick(View view)
    {
        if (isLoggedIn())
        {
            // already logged in
            Intent intent = new Intent(getApplicationContext(), UserProfileMenu.class);
            intent.putExtra(Intent.EXTRA_TEXT, userName);
            startActivity(intent);
        }
        else
        {
            // not logged in
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
    }
}
