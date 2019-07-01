package com.simplisell.presentation.userprofileactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.simplisell.R;

public class ProfileListViewAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] titles;

    ProfileListViewAdapter(Context c,String[] t)
    {
        super(c, R.layout.profile_row,R.id.profile_row_title,t);
        titles=t;
        context=c;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.profile_row,parent,false);

        TextView title=view.findViewById(R.id.profile_row_title);

        title.setText(titles[position]);

        return view;
    }
}
