package com.yibao.biggirl.model.girls;

import com.yibao.biggirl.model.GrilsDataSource;
import com.yibao.biggirl.network.RetrofitHelper;

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
        implements GrilsDataSource
{

    @Override
    public void getGirls(int size, int page, LoadGDataCallback callback) {

        RetrofitHelper.getGankApi()
                      .getGril("福利", size, page)
                      .subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new GirlObserver(callback));
    }


    private class GirlObserver
            implements Observer<GirlBean>
    {
        private LoadGDataCallback mCallback;

        private GirlObserver(LoadGDataCallback callback) {
            mCallback = callback;
        }

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(GirlBean girlBean) {
            mCallback.onLoadDatas(girlBean);


        }

        @Override
        public void onError(Throwable e) {
            mCallback.onDataNotAvailable();
        }

        @Override
        public void onComplete() {

        }
    }


}
