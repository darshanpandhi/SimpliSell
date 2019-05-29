package com.example.crazyeights;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



public class SplashScreen extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);
            thread.start();
        }

        Thread thread = new Thread(){
            @Override
            public void run() {

                try {

                    Thread.sleep(800); // As I am using LENGTH_LONG in Toast
                    startActivity(new Intent(getApplicationContext() , MainActivity.class));
                    finish();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
}


