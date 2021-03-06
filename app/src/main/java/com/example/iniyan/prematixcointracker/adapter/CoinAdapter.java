package com.example.iniyan.prematixcointracker.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.iniyan.prematixcointracker.Interface.ILoadMore;
import com.example.iniyan.prematixcointracker.R;
import com.example.iniyan.prematixcointracker.model.CoinModel;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.iniyan.prematixcointracker.Common.imageUrl;

public class CoinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private ILoadMore iLoadMore;
    private boolean isLoading;
    private Activity activity;
    private List<CoinModel> items;
    private int visibleThreshold = 5, lastVisibleItem, totalItemCount;


    private List<CoinModel> mitemListFilter;


    public CoinAdapter(RecyclerView recyclerView, Activity activity, List<CoinModel> items) {

        this.activity = activity;
        this.items = items;
        this.mitemListFilter = items;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {

                    if (iLoadMore != null)
                        iLoadMore.onLoadMore();
                    isLoading = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(activity).inflate(R.layout.coin_layout, viewGroup, false);

        return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        final CoinModel item = items.get(position);


        CoinViewHolder holderItem = (CoinViewHolder) viewHolder;
        holderItem.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iLoadMore.getAdapter(item);
            }
        });

        holderItem.coin_name.setText(item.getName());
        holderItem.coin_symbol.setText(item.getSymbol());
        holderItem.coin_price.setText(new DecimalFormat("##.##").format(Double.parseDouble(item.price_usd)));
        holderItem.one_hour_change.setText(item.getPercent_change_1h() + "%");

        holderItem.twenty_hours_change.setText(item.getPercent_change_24h() + "%");
        holderItem.seven_days_change.setText(item.getPercent_change_7d() + "%");

        Picasso.with(activity).load(imageUrl +
                item.getSymbol().toLowerCase() + ".png").into(holderItem.coin_icon);


        holderItem.one_hour_change.setTextColor(item.getPercent_change_1h().contains("-") ? Color.parseColor("#FF0000") : Color.parseColor("#32CD32"));
        holderItem.seven_days_change.setTextColor(item.getPercent_change_7d().contains("-") ? Color.parseColor("#FF0000") : Color.parseColor("#32CD32"));
        holderItem.twenty_hours_change.setTextColor(item.getPercent_change_24h().contains("-") ? Color.parseColor("#FF0000") : Color.parseColor("#32CD32"));


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setiLoadMore(ILoadMore iLoadMore) {
        this.iLoadMore = iLoadMore;
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void updateData(List<CoinModel> coinModels) {

        this.items = coinModels;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                Log.e(TAG, "element charString" + charString);

                if (charString.isEmpty()) {
                    mitemListFilter = items;
                } else {
                    List<CoinModel> filteredList = new ArrayList<>();
                    for (CoinModel row : items) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getSymbol().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mitemListFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mitemListFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mitemListFilter = (List<CoinModel>) filterResults.values;

                for (CoinModel coinModel : mitemListFilter) {
                    Log.e(TAG, "element" + coinModel.getName());
                }
                notifyDataSetChanged();

            }
        };
    }


}
