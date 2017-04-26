package com.yibao.biggirl.android;

import com.yibao.biggirl.model.AndroidDataSource;
import com.yibao.biggirl.model.android.AndroidAndGirlBean;

import java.util.List;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/22 10:03
 */
public class AndroidPresenter
        implements AndroidContract.Presenter
{
    private AndroidContract.View     mView;
    private List<AndroidAndGirlBean> mList;
    private RemoteAndroidData        mRemoteAndroidData;

    public AndroidPresenter(AndroidContract.View view) {
        this.mView = view;
        mRemoteAndroidData = new RemoteAndroidData();
        mView.setPrenter(this);
    }


    @Override
    public void start() {
        loadData(20, 1);
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {

    }


    @Override
    public void loadData(int page, int size) {
        mRemoteAndroidData.getGirls(page, size, new AndroidDataSource.LoadADataCallback() {
            @Override
            public void onLoadData(List<AndroidAndGirlBean> list) {
                mView.loadData(list);
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
    }


}
