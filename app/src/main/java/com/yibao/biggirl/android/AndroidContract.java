package com.yibao.biggirl.android;

import com.yibao.biggirl.base.BasePresenter;
import com.yibao.biggirl.base.BaseView;
import com.yibao.biggirl.model.android.AndroidAndGirlBean;

import java.util.List;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/22 10:04
 */
public interface AndroidContract {
    interface View
            extends BaseView<Presenter>
    {
        void loadData(List<AndroidAndGirlBean> list);

    }

    interface Presenter
            extends BasePresenter
    {
        void loadData(int page, int size);
    }
}
