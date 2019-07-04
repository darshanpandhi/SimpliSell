package com.simplisell.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.simplisell.R;

public class SplashScreen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Thread thread = new Thread()
        {   // create a new thread
            @Override
            public void run()
            {

                try
                {

                    Thread.sleep(100); // As I am using LENGTH_LONG in Toast

                    // move to the main activity
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } catch (Exception e)
                {

                    // sent an error message as a toast if we are not able to go to main activity
                    Toast.makeText(getApplicationContext(), "Error: Cant run App", Toast.LENGTH_SHORT).show();
                }
            }
        };

        thread.start();     // start the thread for the transition to mainActivity
    }
}
