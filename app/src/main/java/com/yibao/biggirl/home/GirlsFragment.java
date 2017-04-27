package com.yibao.biggirl.home;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yibao.biggirl.R;
import com.yibao.biggirl.model.girls.ResultsBean;
import com.yibao.biggirl.util.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 作者：Stran on 2017/3/29 01:18
 * 描述：${主列表}
 * 邮箱：strangermy@outlook.com
 */
public class GirlsFragment
        extends Fragment
        implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener
{
    @BindView(R.id.fragment_girl_recycler)
    RecyclerView       mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;

    private GirlsContract.Presenter mPresenter;
    private FloatingActionButton    mFab;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new GirlsPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = View.inflate(getActivity(), R.layout.girls_frag, null);
        unbinder = ButterKnife.bind(this, view);
        //        initData();
        mPresenter.start();
        return view;
    }

    private void initData() {
        mFab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mSwipeRefresh.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);
        mSwipeRefresh.setOnRefreshListener(this);


    }

    private void initRecyclerView(List<ResultsBean> mList) {


        GirlsAdapter mAdapter = new GirlsAdapter(getActivity(), mList);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                                                                            StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        LogUtil.d("555555555555555555555555555555555555");


    }


    @Override
    public void loadData(List<ResultsBean> list) {
        initRecyclerView(list);
    }

    //下拉刷新
    @Override
    public void onRefresh() {

        mPresenter.loadData(20, 1);
        mSwipeRefresh.setRefreshing(false);

    }

    //刷新回调
    @Override
    public void refresh(List<ResultsBean> list) {
        //        mAdapter = new GirlsAdapter(getContext(), list);
        //        mRecyclerView.setAdapter(mAdapter);
        //        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void showError() {
        //        mRecyclerView.setVisibility(View.INVISIBLE);
        //        mIvError.setVisibility(View.VISIBLE);


    }

    @Override
    public void showNormal() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @Override
    public void setPrenter(GirlsContract.Presenter prenter) {
        this.mPresenter = prenter;
    }

    public GirlsFragment newInstance() {

        return new GirlsFragment();
    }


}
