package com.yibao.biggirl.model.girls;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/25 22:59
 */
public interface GirlsDataSource {
    interface LoadGirlsCallback{
        void onLoadGirls(GirlBean girlBean);
        void onDataNotAvailable();

    }

    void getGirls(int page, int size, LoadGirlsCallback callback);

}
