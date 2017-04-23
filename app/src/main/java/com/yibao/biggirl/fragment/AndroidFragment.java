package com.yibao.biggirl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yibao.biggirl.Interface.GirlContract;
import com.yibao.biggirl.Presenter.AndroidPresenter;
import com.yibao.biggirl.R;
import com.yibao.biggirl.adapter.AndroidAdapter;
import com.yibao.biggirl.bean.android.ResultsBeanX;
import com.yibao.biggirl.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/23 06:33
 */
public class AndroidFragment
        extends Fragment
        implements GirlContract.View
{
    GirlContract.Presenter mPresenter;

    @BindView(R.id.android_frag_rv)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault()
                .register(this);
        new AndroidPresenter(this);
        mPresenter.loadData();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = View.inflate(getActivity(), R.layout.android_frag, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    //将数据设置到UI上
    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 1000)
    public void getAndroidDes(List<ResultsBeanX> list) {

        initData(list);
    }

    private void initData(List<ResultsBeanX> list) {
        LogUtil.d(" this And  size ====+ "+list.size());
        AndroidAdapter      adapter = new AndroidAdapter(getContext(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault()
                .unregister(this);
    }

    public AndroidFragment newInstance() {

        return new AndroidFragment();
    }

    @Override
    public void setPrenter(GirlContract.Presenter prenter) {
        this.mPresenter = prenter;
    }
}

