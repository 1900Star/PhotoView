package com.yibao.biggirl.android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yibao.biggirl.R;
import com.yibao.biggirl.model.android.AndroidAndGirlBean;

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
        implements AndroidContract.View, SwipeRefreshLayout.OnRefreshListener
{
    AndroidContract.Presenter mPresenter;
    @BindView(R.id.android_frag_rv)
    RecyclerView       mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;
    private AndroidAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AndroidPresenter(this);
        mPresenter.start();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = View.inflate(getActivity(), R.layout.android_frag, null);

        unbinder = ButterKnife.bind(this, view);
        mSwipeRefresh.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeRefresh.setOnRefreshListener(this);

        return view;
    }


    private void initData(List<AndroidAndGirlBean> list) {
        mAdapter = new AndroidAdapter(getContext(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }


    public AndroidFragment newInstance() {

        return new AndroidFragment();
    }

    @Override
    public void setPrenter(AndroidContract.Presenter prenter) {
        this.mPresenter = prenter;
    }

    @Override
    public void loadData(List<AndroidAndGirlBean> list) {
        initData(list);

    }

    @Override
    public void refresh(List<AndroidAndGirlBean> list) {
        mAdapter = new AndroidAdapter(getContext(), list);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showNormal() {

    }

    @Override
    public void onRefresh() {
        mPresenter.loadData(20, 1);
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();


    }
}

