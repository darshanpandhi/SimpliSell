package com.simplisell.presentation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplisell.R;
import com.simplisell.business.Search;
import com.simplisell.objects.Ad;

import java.util.List;

import static com.simplisell.objects.Category.BOOKS;
import static com.simplisell.objects.Category.JOBS_SERVICES;
import static com.simplisell.objects.Category.TRANSPORTATION;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragmentServicesJobs extends Fragment
{


    private RecyclerView recyclerView;
    private static Search adsSearch = new Search();
    private static List<Ad> ads = adsSearch.getAllAdsByCateogry(JOBS_SERVICES);
    private static boolean isSortedAscending = false;

    public TabFragmentServicesJobs()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment

        ads = adsSearch.getAllAdsByCateogry(JOBS_SERVICES);
        View view=inflater.inflate(R.layout.fragment_tab_fragment_servicesjobs, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.listView_insideFragmentJobs);
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(getContext(),ads);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);


    }

    public static void sort()
    {


        if(isSortedAscending)       // if its already sorted in ascending
        {
            ads=adsSearch.sortPriceDesc(ads);
            isSortedAscending=false;
        }
        else
        {
            ads=adsSearch.sortPriceAsc(ads);
            isSortedAscending=true;
        }
    }
}