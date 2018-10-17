package com.example.iniyan.prematixcointracker.Interface;

import com.example.iniyan.prematixcointracker.model.CoinModel;

public interface ILoadMore {

    void onLoadMore();

    void getAdapter(CoinModel item);
}
