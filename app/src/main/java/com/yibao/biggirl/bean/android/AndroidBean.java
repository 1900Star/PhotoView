package com.yibao.biggirl.bean.android;

import java.util.List;

/**
 * Author：Sid
 * Des：${封装安卓页面的数据}
 * Time:2017/4/23 10:11
 */
public class AndroidBean {
    private List<ResultsBeanX> mList;

    public AndroidBean(List<ResultsBeanX> list) {
        mList = list;
    }

    public List<ResultsBeanX> getList() {
        return mList;
    }

    public void setList(List<ResultsBeanX> list) {
        mList = list;
    }
}
