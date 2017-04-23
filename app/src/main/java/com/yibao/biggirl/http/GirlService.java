package com.yibao.biggirl.http;

import com.yibao.biggirl.bean.android.AndroidDesBean;
import com.yibao.biggirl.bean.girls.GirlBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/5 20:57
 */
public interface GirlService {

    //代码集中营Api 《 www.Gank.io》 福利
    @GET("api/data/{type}/{count}/{page}")
    Observable<GirlBean> getGril(@Path("type") String type,
                                 @Path("count") int count,
                                 @Path("page") int page);
    //Android
    @GET("api/data/{type}/{count}/{page}")
    Observable<AndroidDesBean> getAndroid(@Path("type") String type,
                                          @Path("count") int count,
                                          @Path("page") int page);



}
