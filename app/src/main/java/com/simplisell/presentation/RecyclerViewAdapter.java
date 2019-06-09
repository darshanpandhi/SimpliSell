package com.simplisell.presentation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplisell.R;
import com.simplisell.objects.Ad;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context myContext;
    private List<Ad> myAd;

    public RecyclerViewAdapter(Context context,List<Ad> ad)
    {
        myContext=context;
        myAd=ad;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {

        View view;
        view= LayoutInflater.from(myContext).inflate(R.layout.row,viewGroup,false);

        MyViewHolder viewHolder=new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {

        String title=myAd.get(i).getTitle();
        String price="$"+myAd.get(i).getPrice();

        myViewHolder.adTitle.setText(title);
        myViewHolder.adPrice.setText(price);

    }




    @Override
    public int getItemCount() {
        return myAd.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        private TextView adTitle;
        private TextView adPrice;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            adTitle=(TextView)itemView.findViewById(R.id.row_mainTitle);
            adPrice=(TextView)itemView.findViewById(R.id.row_subTitle);


        }
    }


}
