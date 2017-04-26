package com.yibao.biggirl.model.girls;

import com.yibao.biggirl.model.GrilsDataSource;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/26 00:51
 */
public class GirlsResponstitory
        implements GrilsDataSource
{

    private  RemoteGirlsData mRemoteGirlsData;

    @Override
    public void getGirls(int page, int size, LoadGDataCallback callback) {
        mRemoteGirlsData.getGirls(page,size,callback);

    }



}
