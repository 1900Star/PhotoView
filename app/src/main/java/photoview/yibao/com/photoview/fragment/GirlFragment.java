package photoview.yibao.com.photoview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.GirlsAdapter;
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.util.GirlUitl;
import photoview.yibao.com.photoview.util.LogUtil;

/**
 * 作者：Stran on 2017/3/29 01:18
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlFragment
        extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener


{


    @BindView(R.id.fragment_girl_recycler)
    RecyclerView       mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;
    private View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault()
                .register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (savedInstanceState == null) {
            if (mView == null) {
                mView = View.inflate(getActivity(), R.layout.fragmet_main_girl, null);
                unbinder = ButterKnife.bind(this, mView);
                initData();


            }
        }
        return mView;
    }

    private void initData() {
        GirlUitl.getRx();
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setOnClickListener(view -> LogUtil.d(""));

    }


    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 100) //在ui线程执行 优先级100
    public void onGirlsDataEvent(GirlData data) {


        initRecyclerView(data.getList());

    }

    private void initRecyclerView(List<String> mList) {
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
        unbinder.unbind();
        EventBus.getDefault()
                .unregister(this);
    }


    @OnClick(R.id.swipe_refresh)
    public void onViewClicked() {}

    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //                ImageUitl.getGirls();
            }
        }, 10);
    }
}
