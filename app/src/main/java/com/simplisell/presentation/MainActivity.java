package com.simplisell.presentation;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.simplisell.R;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout=findViewById(R.id.tabview_mainActivity);
        viewPager=findViewById(R.id.view_pager_mainActivity);
        TabPagerAdapter adapter=new TabPagerAdapter(getSupportFragmentManager());

        // adding the fragments
        adapter.addFragment(new TabFragmentAll(),"All");
        adapter.addFragment(new TabFragmentBooks(),"Books");
        adapter.addFragment(new TabFragmentServicesJobs(),"Services & Jobs");
        adapter.addFragment(new TabFragmentArrangement(),"Arrangement");
        adapter.addFragment(new TabFragmentElectronics(),"Electronics");
        adapter.addFragment(new TabFragmentEvents(),"Events");
        adapter.addFragment(new TabFragmentHobbiesGames(),"Hobbies & Games");
        adapter.addFragment(new TabFragmentTransportation(),"Transportation");
        adapter.addFragment(new TabFragmentLiving(),"Living");
        adapter.addFragment(new TabFragmentOther(),"Other");


        // adapter setup

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    public void oneClick(View view){

    }

    public void twoClick(View view){

    }

    public void threeClick(View view){

    }

    public void fourClick(View view){

    }

    public void fiveClick(View view){

    }

    public void sixClick(View view){

    }

    public void sevenClick(View view){

    }

    public void eightClick(View view){

    }
}
