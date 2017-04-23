package com.yibao.biggirl.Presenter;

import com.yibao.biggirl.Interface.GirlContract;
import com.yibao.biggirl.bean.girls.GirlBean;
import com.yibao.biggirl.bean.girls.GirlData;
import com.yibao.biggirl.bean.girls.ResultsBean;
import com.yibao.biggirl.http.GirlRetrofit;
import com.yibao.biggirl.http.GirlService;

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
        implements GirlContract.Presenter
{
    private GirlContract.View mView;

    public GirlsPresenter(GirlContract.View view) {
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
        GirlRetrofit.getRetrofit()
                    .create(GirlService.class)
                    .getGril("福利", 1000, 1)
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
            List<ResultsBean> results = (List<ResultsBean>) girlBean.getResults();
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
