package com.simplisell.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.AccessAds;
import com.simplisell.objects.Ad;

public class ViewAdOfCurrentUser extends AppCompatActivity {

    private final String ADID_TEXT="ADID";

    private Ad currAd;              // holds the curr ad object
    private int adId;               // id of the current ad
    private String title;           // title of the current ad
    private String description;     // description of the current ad
    private double price;           // price of the current ad

    private AccessAds accessAds = new AccessAds();    // helps with accessing ads

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ad_current_user);

        // get id of ad passed from previous activity
        adId = getIntent().getIntExtra(ADID_TEXT, -1);

        currAd = accessAds.getAd(adId);

        // retrieve information of the current ad
        title = currAd.getTitle();
        description = currAd.getDescription();
        price = currAd.getPrice();

        // display corresponding information
        TextView textViewTitle = findViewById(R.id.textView_viewAdCU_title);
        textViewTitle.setText(title);

        TextView textViewDescription = findViewById(R.id.textView_viewAdCU_description);
        textViewDescription.setText(description);

        TextView textViewPrice = findViewById(R.id.textView_viewAdCU_price);
        String price = "$" + this.price;
        textViewPrice.setText(price);
    }

    public void editBtnClick(View view)
    {
        //------------------------------------------------------
        // editBtnClick
        //
        // PURPOSE: let user edit the current ad
        //
        //------------------------------------------------------

        Intent editAd = new Intent(getApplicationContext(), EditAd.class);
        editAd.putExtra(ADID_TEXT, adId);
        startActivity(editAd);
    }

    public void deleteBtnClick(View view)
    {
        //------------------------------------------------------
        // deleteBtnClick
        //
        // PURPOSE: delete the current ad
        //
        //------------------------------------------------------

        accessAds.removeAd(currAd);
        Toast.makeText(getApplicationContext(), "Advertisement Deleted", Toast.LENGTH_LONG).show();

        // go back to main page after deletion
        Intent mainPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainPage);
    }

    public void onBackPressed()
    {
        //------------------------------------------------------
        // onBackPressed
        //
        // PURPOSE: go back to main page if done with editing
        //
        //------------------------------------------------------

        finish();
        Intent mainPage = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainPage);
    }
}
