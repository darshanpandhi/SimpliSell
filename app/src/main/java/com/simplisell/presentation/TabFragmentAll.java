package com.simplisell.presentation;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.simplisell.R;
import com.simplisell.business.Search;
import com.simplisell.objects.Ad;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragmentAll extends Fragment
{

    private RecyclerView recyclerView;
    private static Search adsSearch = new Search();
    private static List<Ad> ads = adsSearch.getAllAds();
    private static boolean isSortedAscending = false;

    public TabFragmentAll()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment

        ads = adsSearch.getAllAds();
        View view=inflater.inflate(R.layout.fragment_tab_fragment_all, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.listView_insideFragmentAll);
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
