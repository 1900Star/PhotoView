package com.yibao.biggirl.Presenter;

import com.yibao.biggirl.Interface.GirlContract;
import com.yibao.biggirl.bean.android.AndroidDesBean;
import com.yibao.biggirl.bean.android.ResultsBeanX;
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
public class AndroidPresenter
        implements GirlContract.Presenter
{
    private GirlContract.View  mView;
    private List<ResultsBeanX> mList;

    public AndroidPresenter(GirlContract.View view) {
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
                    .getAndroid("Android", 1000, 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new AndroidObserver());


    }


    private class AndroidObserver
            implements Observer<AndroidDesBean>
    {


        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(AndroidDesBean androidDesBean) {
            mList = new ArrayList<>();
            List<ResultsBeanX> results = androidDesBean.getResults();
            for (int i = 0; i < results.size(); i++) {

                mList.add(results.get(i));

            }
            EventBus.getDefault()
                    .post(mList);


        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
