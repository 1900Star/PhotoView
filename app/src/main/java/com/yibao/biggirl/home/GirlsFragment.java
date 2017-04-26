package com.yibao.biggirl.home;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yibao.biggirl.R;
import com.yibao.biggirl.model.girls.GirlData;
import com.yibao.biggirl.model.girls.ResultsBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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


    @BindView(R.id.iv_error)
    ImageView mIvError;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;
    @BindView(R.id.fragment_girl_recycler)
    RecyclerView mRecyclerView;
    private GirlsContract.Presenter mPresenter;
    private FloatingActionButton    mFab;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault()
                .register(this);
        new GirlsPresenter(this);
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = View.inflate(getActivity(), R.layout.girls_frag, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mFab = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        mSwipeRefresh.setOnRefreshListener(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


            }
        });


    }

    //拿到数据
    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 100) //在ui线程执行 优先级100
    public void onGirlsDataEvent(GirlData data) {
        //                initRecyclerView(data.getList());

    }

    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 100) //在ui线程执行 优先级100
    public void onGetPositionEvent(int postion) {
        if (postion > 6) {
            mFab.setVisibility(View.VISIBLE);

        }

    }


    @Override
    public void loadData(List<ResultsBean> list) {

        initRecyclerView(list);
    }

    @Override
    public void showError() {
        //        mRecyclerView.setVisibility(View.INVISIBLE);
        //        mIvError.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView(List<ResultsBean> mList) {

        GirlsAdapter adapter = new GirlsAdapter(getActivity(), mList);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                                                                            StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        EventBus.getDefault()
                .unregister(this);
        unbinder.unbind();
    }


    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(false);
        new Handler().postDelayed(() -> {
            //                ImageUitl.getGirls();
        }, 10);
    }

    @Override
    public void setPrenter(GirlsContract.Presenter prenter) {
        this.mPresenter = prenter;
    }

    public GirlsFragment newInstance() {

        return new GirlsFragment();
    }


}
