package com.yibao.biggirl.util;

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
 * 作者：Stran on 2017/3/23 03:23
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class LoadGirlUitl {
    private static final List<String> list = new ArrayList<>();


    public static void getGrils() {

        GirlRetrofit.getRetrofit()
                    .create(GirlService.class)
                    .getGril("福利", 1000, 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new GirlObosever());


    }

    static class GirlObosever
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







