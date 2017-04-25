package com.yibao.biggirl.model.girls;

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
 * Time:2017/4/26 00:52
 */
public class RemoteGirlsData
        implements GirlsDataSource
{
    private static final List<String> list = new ArrayList<>();
    @Override
    public void getGirls(int page, int size, LoadGirlsCallback callback) {

        GirlRetrofit.getGankApi()
                    .getGril("福利", 20, 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new GirlObserver(callback));
    }


    private class GirlObserver
            implements Observer<GirlBean>
    {
        private LoadGirlsCallback mCallback;
        public GirlObserver(LoadGirlsCallback callback) {
            mCallback=callback;
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(GirlBean girlBean) {
            mCallback.onLoadGirls(girlBean);


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
