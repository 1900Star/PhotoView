package com.yibao.biggirl.home;

import com.yibao.biggirl.model.GrilsDataSource;
import com.yibao.biggirl.model.girls.GirlBean;
import com.yibao.biggirl.model.girls.RemoteGirlsData;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/22 10:03
 */
class GirlsPresenter
        implements GirlsContract.Presenter
{
    private GirlsContract.View mView;
    private RemoteGirlsData    mRemoteGirlsData;

    public GirlsPresenter(GirlsContract.View view) {
        this.mView = view;
        mRemoteGirlsData = new RemoteGirlsData();
        mView.setPrenter(this);

    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {

    }


    @Override
    public void start() {
        loadData(20, 1);


    }

    @Override
    public void loadData(int size, int page) {


        mRemoteGirlsData.getGirls(size, page, new GrilsDataSource.LoadGDataCallback() {
            @Override
            public void onLoadDatas(GirlBean girlBean) {

                //                if (isReferesh) {
                //                    mView.refresh(girlBean.getResults());
                //                } else {
                //
                //                }
                mView.loadData(girlBean.getResults());
                //                mView.showNormal();
            }

            @Override
            public void onDataNotAvailable() {
                mView.showError();
            }
        });

    }


}
