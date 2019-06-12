package com.simplisell.presentation.PostingAds;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simplisell.R;
import com.simplisell.objects.Ad;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static String userName = null;
    private final String ADID_TEXT="ADID";

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

        View view ;

        view = LayoutInflater.from(myContext).inflate(R.layout.row,viewGroup,false);

        final MyViewHolder viewHolder=new MyViewHolder(view);

        viewHolder.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int adId=myAd.get(viewHolder.getAdapterPosition()).getAdId();
                String adUserName=myAd.get(viewHolder.getAdapterPosition()).getAdOwner();


                if (adUserName.equals(userName))
                { // logged in user and its their Ad



                    Intent viewAd = new Intent(myContext, ViewAdOfCurrentUser.class);
                    viewAd.putExtra(ADID_TEXT, adId);
                    myContext.startActivity(viewAd);


                }
                else   // not logged in user
                {


                    Intent viewAd = new Intent(myContext, ViewAdOfOtherUser.class);
                    viewAd.putExtra(ADID_TEXT, adId);
                    myContext.startActivity(viewAd);



                }



            }
        });



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {

        String title = myAd.get(i).getTitle();
        double priceDouble = myAd.get(i).getPrice();
        String price ="$" + priceDouble;

        myViewHolder.adTitle.setText(title);

        if(priceDouble==0.0)
        {

            price="FREE";
            myViewHolder.adPrice.setText(price);

        }
        else
        {
            myViewHolder.adPrice.setText(price);
        }


    }


    @Override
    public int getItemCount() {
        return myAd.size();
    }

    public static void login(String uName){ userName=uName;}

    public static void logOut(){userName=null;}



    // HELPER CLASS

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        private TextView adTitle;
        private TextView adPrice;
        private LinearLayout row;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            adTitle=(TextView)itemView.findViewById(R.id.row_mainTitle);
            adPrice=(TextView)itemView.findViewById(R.id.row_subTitle);
            row=(LinearLayout) itemView.findViewById(R.id.row_forAds);


        }
    }


}
