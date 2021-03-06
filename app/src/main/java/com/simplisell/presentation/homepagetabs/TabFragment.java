package com.simplisell.presentation.homepagetabs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplisell.R;
import com.simplisell.business.AccessAds;
import com.simplisell.objects.Ad;
import com.simplisell.objects.AdType;
import com.simplisell.presentation.postingadactivity.RecyclerViewAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment
{

    private AccessAds adsSearch;
    private boolean isSortedAscending = false;

    private List<Ad> ads;

    private List<Ad> filteredAds;


    public TabFragment()
    {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public TabFragment(List<Ad> ads, AccessAds adsSearch)
    {
        this.ads = ads;
        this.adsSearch = adsSearch;

        filteredAds = ads;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listView_insideFragment);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), filteredAds);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    public void sort()
    {
        if (isSortedAscending)       // if its already sorted in ascending
        {
            filteredAds = adsSearch.sortPriceDesc(filteredAds);
            isSortedAscending = false;
        }
        else
        {
            filteredAds = adsSearch.sortPriceAsc(filteredAds);
            isSortedAscending = true;
        }
    }


    // This method calls the filterAdsByType method in accessAds to visually filter an already sorted list of ads
    // (sorted by category). This was originally labeled as a code smell via Rob. However upon further discussions
    // this was revoked. Please see issue https://code.cs.umanitoba.ca/comp3350-summer2019/crazy-eights---8/issues/130
    // for more details.
    public void filterByType(AdType adType)
    {
        filteredAds = adsSearch.filterAdsByType(ads, adType);
    }


    public void revertAds()
    {
        filteredAds = ads;
    }
}
