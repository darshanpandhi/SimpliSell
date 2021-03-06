package com.simplisell.presentation.postingadactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.AccessAds;
import com.simplisell.business.AccessUsers;
import com.simplisell.objects.Ad;
import com.simplisell.objects.User;
import com.simplisell.presentation.MainActivity;

import java.util.Date;

public class ViewAdOfCurrentUser extends AppCompatActivity
{
    private final String USERNAME_TEXT = "USER";
    private final String ADID_TEXT = "ADID";

    private Ad currAd;              // holds the curr ad object
    private User currUser;
    private String userName;          // user looking at the ad
    private int adId;               // id of the current ad
    private String title;           // title of the current ad
    private String description;     // description of the current ad
    private double price;           // price of the current ad
    private Date expireDate;

    private AccessAds accessAds = new AccessAds();    // helps with accessing ads
    private AccessUsers accessUsers = new AccessUsers();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ad_current_user);

        // get id of ad passed from previous activity
        adId = getIntent().getIntExtra(ADID_TEXT, -1);
        userName = getIntent().getStringExtra(USERNAME_TEXT);

        currAd = accessAds.getAd(adId);
        currUser = accessUsers.getUser(userName);

        // retrieve information of the current ad
        title = currAd.getTitle();
        description = currAd.getDescription();
        price = currAd.getPrice();
        expireDate = currAd.getExpiryDate();

        // display corresponding information
        TextView textViewTitle = findViewById(R.id.textView_viewAdCU_title);
        textViewTitle.setText(title);

        TextView textViewDescription = findViewById(R.id.textView_viewAdCU_description);
        textViewDescription.setText(description);

        TextView textViewPrice = findViewById(R.id.textView_viewAdCU_price);
        String price = "$" + this.price;
        textViewPrice.setText(price);

        TextView textViewExpireDate = findViewById(R.id.textView_viewAdCU_expireDate);
        String date = "The ad will expire on " + expireDate;
        textViewExpireDate.setText(date);
    }


    public void editBtnClick(View view)
    {

        Intent editAd = new Intent(getApplicationContext(), EditAd.class);
        editAd.putExtra(ADID_TEXT, adId);
        startActivity(editAd);
    }


    public void repostBtnClick(View view)
    {
        accessAds.repostAd(currAd.getAdId());
        Toast.makeText(getApplicationContext(), "Advertisement Reposted", Toast.LENGTH_LONG).show();

        // go back to main page after repost
        Intent mainPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainPage);
    }


    public void deleteBtnClick(View view)
    {
        accessAds.removeAd(currAd);
        Toast.makeText(getApplicationContext(), "Advertisement Deleted", Toast.LENGTH_LONG).show();

        // go back to main page after deletion
        Intent mainPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainPage);
    }


    @Override
    public void onBackPressed()
    {   // if anytime the back is pressed. Go back
        finish();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(USERNAME_TEXT, userName);
        startActivity(i);
    }
}
