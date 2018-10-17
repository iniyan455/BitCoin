package com.example.iniyan.prematixcointracker;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iniyan.prematixcointracker.Interface.ICoinDetails;
import com.example.iniyan.prematixcointracker.Interface.ILoadMore;
import com.example.iniyan.prematixcointracker.adapter.CoinAdapter;
import com.example.iniyan.prematixcointracker.model.CoinModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements ILoadMore {


//    public MainActivity() {
//    }
//
//    public MainActivity(ICoinDetails iCoinDetails) {
//        this.iCoinDetails = iCoinDetails;
//    }

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    List<CoinModel> items = new ArrayList<>();
    CoinAdapter coinAdapter;
    RecyclerView recyclerView;
    OkHttpClient okHttpClient;
    Request request;
    SwipeRefreshLayout swipeRefreshLayout;
    ICoinDetails iCoinDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_to_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorPrimaryDark), getResources().getColor(R.color.colorPrimary));

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadFirst10Coin();


            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                loadFirst10Coin();
                setupAdapter();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.coin_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        setupAdapter();
    }

    private void setupAdapter() {
        coinAdapter = new CoinAdapter(recyclerView, MainActivity.this, items);
        recyclerView.setAdapter(coinAdapter);

        coinAdapter.setiLoadMore(this);
    }

    private void loadNext10Coin(int index) {


        okHttpClient = new OkHttpClient();
        request = new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10", index))
                .build();

        swipeRefreshLayout.setRefreshing(true);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                String body = response.body().string();
                Gson gson = new Gson();
                final List<CoinModel> newItems = gson.fromJson(body, new TypeToken<List<CoinModel>>() {
                }.getType());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.addAll(newItems);
                        coinAdapter.setLoaded();
                        coinAdapter.updateData(items);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });
    }

    private void loadFirst10Coin() {
        okHttpClient = new OkHttpClient();
        request = new Request.Builder()
                .url("https://api.coinmarketcap.com/v1/ticker/?start=0&limit=10")
                .build();

        swipeRefreshLayout.setRefreshing(true);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {


                String body = response.body().string();
                Gson gson = new Gson();


                final List<CoinModel> newItems = gson.fromJson(body, new TypeToken<List<CoinModel>>() {
                }.getType());


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        coinAdapter.updateData(newItems);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

                //if (swipeRefreshLayout.isRefreshing())
            }
        });
    }


    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {


                    case R.id.buycryptocurrency:
                        drawerLayout.closeDrawers();

                        startActivity(new Intent(MainActivity.this, BuyCryptocurrency.class));

                        Toast.makeText(getApplicationContext(), "buycryptocurrency", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.favourites:
                        Toast.makeText(getApplicationContext(), "favourites", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.alarm:
                        Toast.makeText(getApplicationContext(), "alarm", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.cryptodaily:
                        Toast.makeText(getApplicationContext(), "cryptodaily", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, CryptoDaily.class));

                        drawerLayout.closeDrawers();
                        break;

                    case R.id.report_bug:
                        Toast.makeText(getApplicationContext(), "bug", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();

                        String[] TO = {"paypre@prematix.com"};
                        String[] CC = {"paypre@prematix.com"};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);

                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_CC, CC);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Bug Found From User");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }


                        break;
                    case R.id.share:
                        Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        try {
                            Intent i = new Intent(Intent.ACTION_SEND);
                            i.setType("text/plain");
                            i.putExtra(Intent.EXTRA_SUBJECT, "BitCoin Market App");
                            String sAux = "\nLet Prematix recommend you this application Newly \n\n";
                            sAux = sAux + "https://play.google.com/store/apps/details?id=com.examp.three&hl=en \n\n";
                            i.putExtra(Intent.EXTRA_TEXT, sAux);
                            startActivity(Intent.createChooser(i, "choose one"));
                        } catch (Exception e) {
                            //e.toString();
                        }


                        break;
                    case R.id.like:
                        Toast.makeText(getApplicationContext(), "like", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.examp.three&hl=en")));


                        break;


                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
//        TextView tv_email = (TextView) header.findViewById(R.id.tv_email);
//        tv_email.setText("raj.amalw@learn2crack.com");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onLoadMore() {

        if (items.size() <= Common.MAX_COIN_LOAD) {
            loadNext10Coin(items.size());
        } else
            Toast.makeText(getApplicationContext(), "Data max is " + Common.MAX_COIN_LOAD, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getAdapter(CoinModel item) {

        Toast.makeText(getApplicationContext(), "Data max is " + item.getId() + Common.MAX_COIN_LOAD, Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(MainActivity.this, CoinDetails.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("namee", item.getName());
        intent.putExtra("symbol", item.getSymbol());
        intent.putExtra("price", item.getPrice_usd());
        intent.putExtra("price_btc", item.getPrice_btc());
        intent.putExtra("percent_1hr", item.getPercent_change_1h());
        intent.putExtra("percent_24h", item.getPercent_change_24h());
        intent.putExtra("percent_7d", item.getPercent_change_7d());
        intent.putExtra("rank", item.getRank());
        intent.putExtra("twentyfour_volume", item.getTwentyfour_volume());
        intent.putExtra("market_cap", item.getMarket_cap_usd());
        intent.putExtra("availablesupply", item.getAvailable_supply());
        intent.putExtra("totalsupply", item.getTotal_supply());
        intent.putExtra("max_supply", item.getMax_supply());
        intent.putExtra("lastupdate", item.getLast_updated());


        startActivity(intent);

    }


}
