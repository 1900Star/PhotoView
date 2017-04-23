package com.yibao.biggirl.Interface;

import com.yibao.biggirl.bean.base.BaseView;
import com.yibao.biggirl.bean.base.BasePresenter;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/22 10:04
 */
public interface GirlContract {
    interface View
            extends BaseView<Presenter>
    {


    }

    interface Presenter
            extends BasePresenter
    {
        void loadData();
    }
}
