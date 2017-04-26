package com.yibao.biggirl.model;

import com.yibao.biggirl.model.android.AndroidAndGirlBean;

import java.util.List;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/25 22:59
 */
public interface AndroidDataSource {
    //这个接口用于将数据回调给对应Presenter层
    interface LoadADataCallback {
        void onLoadData(List<AndroidAndGirlBean> list);

        void onDataNotAvailable();

    }

    void getGirls(int page, int size, LoadADataCallback callback);

}
