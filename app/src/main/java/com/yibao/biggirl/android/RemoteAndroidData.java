package com.yibao.biggirl.android;

import com.yibao.biggirl.model.AndroidDataSource;
import com.yibao.biggirl.model.android.AndroidAndGirlBean;
import com.yibao.biggirl.model.android.AndroidDesBean;
import com.yibao.biggirl.model.girls.GirlBean;
import com.yibao.biggirl.network.RetrofitHelper;

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
 * Time:2017/4/26 00:52
 */
public class RemoteAndroidData
        implements AndroidDataSource
{
    private List<AndroidAndGirlBean> mList;

    @Override
    public void getGirls(int page, int size, LoadADataCallback callback) {
        Observable.zip(RetrofitHelper.getGankApi()
                                     .getAndroid("Android", page, size),
                       RetrofitHelper.getGankApi()
                                     .getGril("福利", page, size),
                       this::zipHelper)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new AndroidObserver(callback));

    }


    private class AndroidObserver
            implements Observer<AndroidAndGirlBean>
    {
        private LoadADataCallback mCallback;

        private AndroidObserver(LoadADataCallback callback) {
            mCallback = callback;
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(AndroidAndGirlBean bean) {
            mList = new ArrayList<>();
            for (int i = 0; i < bean.mGrilData.size(); i++) {
                AndroidAndGirlBean itemData = new AndroidAndGirlBean(bean.mAndroidData,
                                                                     bean.mGrilData);
                mList.add(itemData);
            }
                                    mCallback.onLoadData(mList);

            //将数据发到
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
