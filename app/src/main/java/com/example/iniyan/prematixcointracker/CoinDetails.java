package com.example.iniyan.prematixcointracker;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iniyan.prematixcointracker.Interface.ICoinDetails;
import com.example.iniyan.prematixcointracker.Interface.ILoadMore;
import com.example.iniyan.prematixcointracker.model.CoinModel;
import com.squareup.picasso.Picasso;

import static com.example.iniyan.prematixcointracker.Common.imageUrl;

public class CoinDetails extends AppCompatActivity implements ICoinDetails {
    ImageView imageView;
    TextView txt_name, txt_symbol, txt_price, txt_price_btc, txt_percent_1hr, txt_percent_24hr, txt_percent_7d, txt_percent_twentyfour_volume, txt_rank,
            txt_marketcap, txt_totalamount, txt_value, txt_market_cap, txt_availablesupply, txt_max_supply, txt_lastupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Coin Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView) findViewById(R.id.image_icon);
        txt_name = (TextView) findViewById(R.id.name);

        txt_symbol = (TextView) findViewById(R.id.symbol);
        txt_price = (TextView) findViewById(R.id.priceusd);
        txt_price_btc = (TextView) findViewById(R.id.pricebtc);
        txt_percent_1hr = (TextView) findViewById(R.id.percentchange_1h);
        txt_percent_24hr = (TextView) findViewById(R.id.percentchange_24h);
        txt_percent_7d = (TextView) findViewById(R.id.percentchange_7d);
        txt_percent_twentyfour_volume = (TextView) findViewById(R.id.twentyFourHour_volume);
        txt_rank = (TextView) findViewById(R.id.rank);
        txt_market_cap = (TextView) findViewById(R.id.marketcap);
        txt_availablesupply = (TextView) findViewById(R.id.circulatingsupply);
        txt_value = (TextView) findViewById(R.id.value);
        txt_totalamount = (TextView) findViewById(R.id.totalamount);
        txt_marketcap = (TextView) findViewById(R.id.marketcap);


        txt_max_supply = (TextView) findViewById(R.id.max_supply);
        txt_lastupdate = (TextView) findViewById(R.id.lastupdate);


        String id = getIntent().getExtras().getString("id");
        String name = getIntent().getExtras().getString("name");
        String symbol = getIntent().getExtras().getString("symbol");
        String price = getIntent().getExtras().getString("price");
        String price_btc = getIntent().getExtras().getString("price_btc");
        String percent_1hr = getIntent().getExtras().getString("percent_1hr");
        String percent_24h = getIntent().getExtras().getString("percent_24h");
        String percent_7d = getIntent().getExtras().getString("percent_7d");
        String rank = getIntent().getExtras().getString("rank");
        String twentyfour_volume = getIntent().getExtras().getString("twentyfour_volume");
        String market_cap = getIntent().getExtras().getString("market_cap");
        String availablesupply = getIntent().getExtras().getString("availablesupply");
        String totalsupply = getIntent().getExtras().getString("totalsupply");
        String max_supply = getIntent().getExtras().getString("max_supply");
        String lastupdate = getIntent().getExtras().getString("lastupdate");
        Picasso.with(this).load(imageUrl +
                symbol.toLowerCase() + ".png").into(imageView);


        txt_lastupdate.setText(lastupdate);
        txt_max_supply.setText(max_supply);
        txt_totalamount.setText(totalsupply);
        txt_name.setText(name);
        txt_value.setText(percent_24h);
        txt_price_btc.setText(price_btc);
        txt_price.setText(price);
        txt_availablesupply.setText(availablesupply);
        txt_percent_1hr.setText(percent_1hr);
        txt_percent_24hr.setText(percent_24h);
        txt_market_cap.setText(market_cap);
        txt_rank.setText(rank);
        txt_percent_twentyfour_volume.setText(twentyfour_volume);
        txt_percent_7d.setText(percent_7d);
        txt_symbol.setText(symbol);
        txt_marketcap.setText(market_cap);


        txt_percent_1hr.setTextColor(percent_1hr.contains("-") ? Color.parseColor("#FF0000") : Color.parseColor("#32CD32"));
        txt_percent_7d.setTextColor(percent_7d.contains("-") ? Color.parseColor("#FF0000") : Color.parseColor("#32CD32"));
        txt_percent_24hr.setTextColor(percent_24h.contains("-") ? Color.parseColor("#FF0000") : Color.parseColor("#32CD32"));


        txt_value.setTextColor(percent_24h.contains("-") ? Color.parseColor("#FF0000") : Color.parseColor("#32CD32"));


    }


    @Override
    public void coindetails(CoinModel item) {
        Toast.makeText(getApplicationContext(), "coin " + item.getId(), Toast.LENGTH_SHORT).show();
    }
}
