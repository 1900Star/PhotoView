package com.yibao.biggirl.home;

import com.yibao.biggirl.base.BasePresenter;
import com.yibao.biggirl.base.BaseView;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/22 10:04
 */
public interface GirlsContract {
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
