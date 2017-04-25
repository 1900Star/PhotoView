package com.yibao.biggirl.model.girls;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/26 00:51
 */
public class GirlsResponstitory
        implements GirlsDataSource
{

    private  RemoteGirlsData mRemoteGirlsData;

    @Override
    public void getGirls(int page, int size, LoadGirlsCallback callback) {
        mRemoteGirlsData.getGirls(page,size,callback);

    }



}
