package com.example.iniyan.prematixcointracker.model;

public class CoinModel {

    public String id;
    public String name;
    public String symbol;
    public String price_usd;
    public String percent_change_1h;
    public String percent_change_24h;
    public String percent_change_7d;
    public String rank;
    public String price_btc;
    public String twentyfour_volume;
    public String market_cap_usd;
    public String available_supply;
    public String total_supply;
    public String max_supply;
    public String last_updated;

    public CoinModel(String id, String name, String symbol, String price_usd, String percent_change_1h, String percent_change_24h, String percent_change_7d, String rank, String price_btc, String twentyfour_volume, String market_cap_usd, String available_supply, String total_supply, String max_supply, String last_updated) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price_usd = price_usd;
        this.percent_change_1h = percent_change_1h;
        this.percent_change_24h = percent_change_24h;
        this.percent_change_7d = percent_change_7d;
        this.rank = rank;
        this.price_btc = price_btc;
        this.twentyfour_volume = twentyfour_volume;
        this.market_cap_usd = market_cap_usd;
        this.available_supply = available_supply;
        this.total_supply = total_supply;
        this.max_supply = max_supply;
        this.last_updated = last_updated;
    }

    public String getRank() {

        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String getTwentyfour_volume() {
        return twentyfour_volume;
    }

    public void setTwentyfour_volume(String twentyfour_volume) {
        this.twentyfour_volume = twentyfour_volume;
    }

    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(String market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(String available_supply) {
        this.available_supply = available_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(String max_supply) {
        this.max_supply = max_supply;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public CoinModel() {
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public CoinModel(String id, String name, String symbol, String price_usd, String percent_change_1h, String percent_change_24h, String percent_change_7d) {

        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price_usd = price_usd;
        this.percent_change_1h = percent_change_1h;
        this.percent_change_24h = percent_change_24h;
        this.percent_change_7d = percent_change_7d;
    }
}
