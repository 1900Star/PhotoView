package com.yibao.biggirl.home;

import com.yibao.biggirl.model.girls.GirlBean;
import com.yibao.biggirl.model.girls.GirlData;
import com.yibao.biggirl.model.girls.RemoteGirlsData;
import com.yibao.biggirl.model.girls.ResultsBean;
import com.yibao.biggirl.network.GirlRetrofit;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/22 10:03
 */
public class GirlsPresenter
        implements GirlsContract.Presenter
{
    private GirlsContract.View mView;
    private RemoteGirlsData mRemoteGirlsData;

    public GirlsPresenter(GirlsContract.View view) {
        this.mView = view;

        mView.setPrenter(this);
    }

    private static final List<String> list = new ArrayList<>();

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {

    }


    @Override
    public void loadData() {


        GirlRetrofit.getGankApi()
                    .getGril("福利", 20, 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new GirlObserver());
    }


    private class GirlObserver
            implements Observer<GirlBean>
    {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(GirlBean girlBean) {


            List<ResultsBean> results = girlBean.getResults();
            for (int i = 0; i < results.size(); i++) {

                list.add(results.get(i)
                                .getUrl());

            }
            EventBus.getDefault()
                    .post(new GirlData(list));

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
