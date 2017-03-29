package photoview.yibao.com.photoview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.GirlAdapter;
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.util.LogUtil;

/**
 * 作者：Stran on 2017/3/29 01:18
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlFragment
        extends Fragment
{

    @BindView(R.id.fragment_girl_recycler)
    RecyclerView       mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;
    public View mView;

    private ArrayList<GirlData> mList;
    private RecyclerView        mRv;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        mView = View.inflate(getActivity(), R.layout.fragmet_girl, null);
        mRv = (RecyclerView) mView.findViewById(R.id.fragment_girl_recycler);
        initData();


        unbinder = ButterKnife.bind(this, mView);
        return mView;
    }

    private void initData() {
        if (mRecyclerView == null) {
            LogUtil.d("777777777777777777777777");

        }

        GirlAdapter         adapter  = new GirlAdapter(getActivity());
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                                                                            StaggeredGridLayoutManager.VERTICAL);
        //        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);
        mRv.setHasFixedSize(true);
        mRv.setItemAnimator(new DefaultItemAnimator());
        //        mRv.setAd
        mRv.setAdapter(adapter);
    }


    @OnClick({R.id.fragment_girl_recycler,
              R.id.swipe_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_girl_recycler:
                break;
            case R.id.swipe_refresh:
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
