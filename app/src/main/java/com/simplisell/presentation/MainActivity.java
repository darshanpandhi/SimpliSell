package com.simplisell.presentation;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.simplisell.R;
import com.simplisell.objects.User;
import com.simplisell.objects.UserAdvertiser;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private User currUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            currUser=(UserAdvertiser) getIntent().getSerializableExtra("User");

        }catch (Exception e){
            currUser=null;
        }
        currUser=(UserAdvertiser) getIntent().getSerializableExtra("User");


        tabLayout = findViewById(R.id.tabview_mainActivity);
        viewPager = findViewById(R.id.view_pager_mainActivity);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());

        // adding the fragments
        adapter.addFragment(new TabFragmentAll(), "All");
        adapter.addFragment(new TabFragmentBooks(), "Books");
        adapter.addFragment(new TabFragmentServicesJobs(), "Services & Jobs");
        adapter.addFragment(new TabFragmentArrangement(), "Arrangement");
        adapter.addFragment(new TabFragmentElectronics(), "Electronics");
        adapter.addFragment(new TabFragmentEvents(), "Events");
        adapter.addFragment(new TabFragmentHobbiesGames(), "Hobbies & Games");
        adapter.addFragment(new TabFragmentTransportation(), "Transportation");
        adapter.addFragment(new TabFragmentLiving(), "Living");
        adapter.addFragment(new TabFragmentOther(), "Other");


        // adapter setup

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



    }
}