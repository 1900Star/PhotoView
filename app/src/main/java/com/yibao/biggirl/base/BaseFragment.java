package com.yibao.biggirl.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 作者：Stran on 2017/3/29 15:24
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public abstract class BaseFragment
        extends Fragment
{

    protected BaseActivity mActivity;

    protected abstract void initView(View view, Bundle savedInstanceState);
    protected  void initListener(){};

    //获取fragment布局文件ID
    protected abstract int getLayoutId();

    //获取宿主Activity
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }


    //添加fragment
    protected void addFragment(BaseFragment fragment) {
        if (null != fragment) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view, savedInstanceState);
        initListener();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
