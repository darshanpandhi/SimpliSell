package com.simplisell.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.application.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class SplashScreen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        copyDatabaseToDevice();

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
                }
                catch (Exception e)
                {

                    // sent an error message as a toast if we are not able to go to main activity
                    Toast.makeText(getApplicationContext(), "Error: Cant run App", Toast.LENGTH_SHORT).show();
                }
            }
        };

        thread.start();     // start the thread for the transition to mainActivity
    }


    private void copyAssetsToDirectory(String[] assets, File directory) throws IOException
    {
        AssetManager assetManager = getAssets();

        for (String asset : assets)
        {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            //check if database file already exit?
            if (!outFile.exists())
            {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1)
                {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }



    private void copyDatabaseToDevice()
    {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try
        {
            //load in all asset file
            assetNames = assetManager.list(DB_PATH);

            if (assetNames != null)
            {
                for (int i = 0; i < assetNames.length; i++)
                {
                    assetNames[i] = DB_PATH + "/" + assetNames[i];
                }

                copyAssetsToDirectory(assetNames, dataDirectory);

                Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());
            }
        }
        catch (final IOException ioe)
        {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }
}
