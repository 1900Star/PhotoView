package com.yibao.biggirl.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yibao.biggirl.R;
import com.yibao.biggirl.model.android.AndroidAndGirlBean;
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
        implements AndroidContract.View
{
    AndroidContract.Presenter mPresenter;

    @BindView(R.id.android_frag_rv)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("==========  onCreate");
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
        LogUtil.d("==========  onCreateView");
        View view = View.inflate(getActivity(), R.layout.android_frag, null);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }


    //将数据设置到UI上
    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 1000)
    public void getAndroidDes(List<AndroidAndGirlBean> list) {

        initData(list);
    }

    private void initData(List<AndroidAndGirlBean> list) {
        AndroidAdapter      adapter = new AndroidAdapter(getContext(), list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d("==========  onDestroyView");
        unbinder.unbind();
        EventBus.getDefault()
                .unregister(this);


    }

    public AndroidFragment newInstance() {

        return new AndroidFragment();
    }

    @Override
    public void setPrenter(AndroidContract.Presenter prenter) {
        this.mPresenter = prenter;
    }
}

