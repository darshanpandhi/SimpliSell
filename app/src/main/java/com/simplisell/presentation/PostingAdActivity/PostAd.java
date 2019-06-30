package com.simplisell.presentation.PostingAdActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.simplisell.objects.Ad;
import com.simplisell.business.AccessAds;

import com.simplisell.R;
import com.simplisell.objects.AdType;
import com.simplisell.objects.Category;
import com.simplisell.presentation.UserProfileActivity.UserProfileMenu;

public class PostAd extends AppCompatActivity
{
    private final String USERNAME_TEXT = "USER";
    private final String ADID_TEXT = "ADID";

    private String userName;

    // hardcode ad type for iteration 1
    private AdType adType = AdType.OFFERING;

    private Category category = null;

    private EditText title;
    private EditText description;
    private EditText price;

    private AccessAds accessAds;

    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_ad);

        userName = getIntent().getStringExtra(USERNAME_TEXT);

        // initializing edit text boxes
        title = findViewById(R.id.editText_postAd_title);
        description = findViewById(R.id.editText_postAd_description);
        price = findViewById(R.id.editText_postAd_price);

        accessAds = new AccessAds();
    }


    public void postBtnClick(View view)
    {
        String title;
        String description;
        double price;

        // check if all fields are filled
        boolean titleEmpty = this.title.getText().toString().isEmpty();
        boolean descriptionEmpty = this.description.getText().toString().isEmpty();
        boolean priceEmpty = this.price.getText().toString().isEmpty();
        boolean categoryEmpty = (category == null);

        // if empty field exists
        if (titleEmpty || descriptionEmpty || priceEmpty || categoryEmpty)
        {
            Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            title = this.title.getText().toString().trim();
            description = this.description.getText().toString().trim();
            price = Double.parseDouble(this.price.getText().toString());

            // add ad into database
            Ad ad = new Ad(userName, adType, category, title, description, price);
            accessAds.insertAd(ad);
            Toast.makeText(getApplicationContext(), "Advertisement Posted", Toast.LENGTH_LONG).show();

            // go to View Individual Ad activity (current user)
            finish();
            Intent viewAd = new Intent(getApplicationContext(), ViewAdOfCurrentUser.class);
            viewAd.putExtra(ADID_TEXT, ad.getAdId());
            startActivity(viewAd);
        }
    }


    public void selectCategoryBtnClick(View view)
    {
        AlertDialog dialog;
        AlertDialog.Builder builder;
        final String[] categories = {"ELECTRONICS", "BOOKS", "ACCOMMODATION", "JOBS_SERVICES", "TRANSPORTATION", "EVENTS", "OTHERS"};

        builder = new AlertDialog.Builder(PostAd.this);

        builder.setTitle("Select the Category");

        builder.setSingleChoiceItems(categories, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                result = categories[which];
                category = Category.valueOf(result);
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                TextView textView = findViewById(R.id.textView_postAd_category);
                textView.setText(result);
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });

        dialog = builder.create();
        dialog.show();
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
