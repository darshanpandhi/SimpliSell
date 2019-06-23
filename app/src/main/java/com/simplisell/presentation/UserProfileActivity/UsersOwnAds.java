package com.simplisell.presentation.UserProfileActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.simplisell.R;
import com.simplisell.business.Search;
import com.simplisell.objects.Ad;
import com.simplisell.presentation.PostingAdActivity.RecyclerViewAdapter;

import java.util.List;

import static com.simplisell.objects.Category.BOOKS;

public class UsersOwnAds extends AppCompatActivity
{

    private static final String USERNAME_TEXT="USER";

    private List<Ad> ads;
    private Search adsSearch;
    private RecyclerView recyclerView;
    private String userName;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_own_ads);
        userName = getIntent().getStringExtra(USERNAME_TEXT);


        adsSearch = new Search();
        ads = adsSearch.getUserSpecificAds(userName);


        recyclerView = (RecyclerView) findViewById(R.id.profile_usersAds_recycle_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, ads);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    @Override
    public void onBackPressed()
    {   // if anytime the back is pressed. Go back

        Intent i = new Intent(this,UserProfileMenu.class);
        startActivity(i);
    }



}
