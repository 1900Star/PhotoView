package com.yibao.biggirl.android;

import com.yibao.biggirl.model.android.AndroidAndGirlBean;
import com.yibao.biggirl.model.android.AndroidDesBean;
import com.yibao.biggirl.model.girls.GirlBean;
import com.yibao.biggirl.network.GirlRetrofit;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
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
        implements AndroidContract.Presenter
{
    private AndroidContract.View     mView;
    private List<AndroidAndGirlBean> mList;

    public AndroidPresenter(AndroidContract.View view) {
        this.mView = view;
        mView.setPrenter(this);
    }


    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {

    }


    @Override
    public void loadData() {
        Observable.zip(GirlRetrofit.getGankApi()
                                   .getAndroid("Android", 20, 1),
                       GirlRetrofit.getGankApi()
                                   .getGril("福利", 20, 1),
                       this::zipHelper)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new AndroidObserver());

    }


    private class AndroidObserver
            implements Observer<AndroidAndGirlBean>
    {


        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(AndroidAndGirlBean item) {
            mList = new ArrayList<>();
            for (int i = 0; i < item.mGrilData.size(); i++) {
                AndroidAndGirlBean itemData = new AndroidAndGirlBean(item.mAndroidData, item.mGrilData);
                mList.add(itemData);
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

    private AndroidAndGirlBean zipHelper(AndroidDesBean androidDesBean, GirlBean girlBean) {
        AndroidAndGirlBean item = new AndroidAndGirlBean();
        for (int i = 0; i < girlBean.getResults()
                                    .size(); i++) {

            item.mAndroidData = androidDesBean.getResults();
            item.mGrilData = girlBean.getResults();

        }


        return item;
    }
}
