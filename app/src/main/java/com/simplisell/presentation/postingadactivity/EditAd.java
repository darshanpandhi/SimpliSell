package com.simplisell.presentation.postingadactivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.simplisell.R;
import com.simplisell.business.AccessAds;
import com.simplisell.objects.Ad;
import com.simplisell.objects.Category;
import com.simplisell.presentation.MainActivity;

public class EditAd extends AppCompatActivity
{

    private final String ADID_TEXT = "ADID";

    private Ad currAd;                  // holds the curr ad object
    private int adId;                   // id of the current ad
    private Category category = null;   // category of the current ad
    private String title;               // title of the current ad
    private String description;         // description of the current ad
    private double price;               // price of the current ad

    private AccessAds accessAds = new AccessAds();    // helps with accessing ads

    private String result;              // helps with displaying the chosen category


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ad);

        // get id of ad passed from previous activity
        adId = getIntent().getIntExtra(ADID_TEXT, -1);

        currAd = accessAds.getAd(adId);

        // retrieve information of the current ad
        category = currAd.getCategory();
        title = currAd.getTitle();
        description = currAd.getDescription();
        price = currAd.getPrice();

        // generate original information of the ad
        TextView textViewCategory = findViewById(R.id.textView_editAd_category);
        textViewCategory.setText(category.name());

        EditText editTextTitle = findViewById(R.id.editText_editAd_title);
        editTextTitle.setText(title);

        EditText editTextDescription = findViewById(R.id.editText_editAd_description);
        editTextDescription.setText(description);

        EditText editTextPrice = findViewById(R.id.editText_editAd_price);
        String price = String.valueOf(this.price);
        editTextPrice.setText(price);
    }


    public void saveBtnClick(View view)
    {
        String title;
        String description;
        double price;

        // Show a progress Dialog while the authentication is loading
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving");
        progressDialog.show();

        // check if all fields are filled
        EditText editTextTitle = findViewById(R.id.editText_editAd_title);
        boolean titleEmpty = editTextTitle.getText().toString().isEmpty();

        EditText editTextDescription = findViewById(R.id.editText_editAd_description);
        boolean descriptionEmpty = editTextDescription.getText().toString().isEmpty();

        EditText editTextPrice = findViewById(R.id.editText_editAd_price);
        boolean priceEmpty = editTextPrice.getText().toString().isEmpty();

        boolean categoryEmpty = (category == null);

        // if empty field exists
        if (titleEmpty || descriptionEmpty || priceEmpty || categoryEmpty)
        {
            Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            title = editTextTitle.getText().toString().trim();
            description = editTextDescription.getText().toString().trim();
            price = Double.parseDouble(editTextPrice.getText().toString());

            // save changes made by user
            currAd.setCategory(category);
            currAd.setTitle(title);
            currAd.setDescription(description);
            currAd.setPrice(price);
            accessAds.updateAd(currAd);
            Toast.makeText(getApplicationContext(), "Advertisement Updated", Toast.LENGTH_LONG).show();

            // go to View Individual Ad activity (current user)
            Intent viewAd = new Intent(getApplicationContext(), ViewAdOfCurrentUser.class);
            viewAd.putExtra(ADID_TEXT, currAd.getAdId());
            progressDialog.dismiss();
            startActivity(viewAd);
        }
    }


    public void selectCategoryBtnClick(View view)
    {
        AlertDialog dialog;
        AlertDialog.Builder builder;
        final String[] categories = {"ELECTRONICS", "BOOKS", "ACCOMMODATION", "JOBS_SERVICES", "TRANSPORTATION", "EVENTS", "OTHERS"};

        builder = new AlertDialog.Builder(EditAd.this);

        builder.setTitle("Select the Category");

        // set information regarding the chosen category
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
                TextView textView = findViewById(R.id.textView_editAd_category);
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
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
