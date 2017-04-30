package com.yibao.biggirl.android;

import com.yibao.biggirl.model.android.AndroidDataSource;
import com.yibao.biggirl.model.android.AndroidAndGirl;
import com.yibao.biggirl.model.android.RemoteAndroidData;

import java.util.List;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/22 10:03
 */
public class AndroidPresenter
        implements AndroidContract.Presenter
{
    private AndroidContract.View mView;
    private RemoteAndroidData    mRemoteAndroidData;

    public AndroidPresenter(AndroidContract.View view) {
        this.mView = view;
        mRemoteAndroidData = new RemoteAndroidData();
        mView.setPrenter(this);
    }


    @Override
    public void start() {
        loadData(200, 1);
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
            public void onLoadData(List<AndroidAndGirl> list) {

                    mView.loadData(list);
//                mView.showNormal();
            }

            @Override
            public void onDataNotAvailable() {
                mView.showError();

            }
        });
    }


}
