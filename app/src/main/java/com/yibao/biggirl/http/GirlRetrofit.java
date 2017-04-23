package com.yibao.biggirl.http;


import com.yibao.biggirl.MyApplication;
import com.yibao.biggirl.util.Constans;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/10 17:22
 */
public class GirlRetrofit {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (GirlRetrofit.class) {
                retrofit = new Retrofit.Builder().baseUrl(Constans.GANK_API)
                                                 .addConverterFactory(GsonConverterFactory.create())
                                                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                                 .client(MyApplication.defaultOkHttpClient())
                                                 .build();

            }

        }
        return retrofit;


    }

}
