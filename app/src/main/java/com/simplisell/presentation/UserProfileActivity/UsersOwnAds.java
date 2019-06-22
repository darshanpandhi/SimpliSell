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

    private List<Ad> ads;
    private Search adsSearch;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_own_ads);

        adsSearch = new Search();
        ads = adsSearch.getAllAdsByCategory(BOOKS);

        recyclerView = (RecyclerView) findViewById(R.id.profile_usersAds_recycle_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, ads);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    public void backButtonClick(View view)
    {

        finish();
        startActivity(new Intent(getApplicationContext() , UserProfileMenu.class));
    }
}
