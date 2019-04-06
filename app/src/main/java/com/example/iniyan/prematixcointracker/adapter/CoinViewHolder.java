package com.example.iniyan.prematixcointracker.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iniyan.prematixcointracker.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class CoinViewHolder extends RecyclerView.ViewHolder {
    public ImageView coin_icon;
    public TextView coin_symbol, coin_name, coin_price, one_hour_change, twenty_hours_change, seven_days_change;
    CardView cardview;


    public CoinViewHolder(@NonNull View itemView) {
        super(itemView);
         cardview = (CardView) itemView.findViewById(R.id.cardview);
        coin_icon = (ImageView) itemView.findViewById(R.id.coinIcon);
        coin_name = (TextView) itemView.findViewById(R.id.coinName);
        coin_price = (TextView) itemView.findViewById(R.id.priceUsd);
        coin_symbol = (TextView) itemView.findViewById(R.id.coinSymbol);

        one_hour_change = (TextView) itemView.findViewById(R.id.oneHour);
        twenty_hours_change = (TextView) itemView.findViewById(R.id.twentyFourHour);
        seven_days_change = (TextView) itemView.findViewById(R.id.sevenDay);



    }
}
