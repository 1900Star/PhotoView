package photoview.yibao.com.photoview.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.GirlsAdapter;
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.util.ImageUitl;
import photoview.yibao.com.photoview.util.LogUtil;

/**
 * 作者：Stran on 2017/3/29 01:18
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlFragment
        extends Fragment
        implements ImageUitl.OnData

{


    public View mView;


    @BindView(R.id.fragment_girl_recycler)
    RecyclerView       mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private ArrayList<GirlData> mList;
    private AppCompatActivity   mActivity;
    private GirlsAdapter        mAdapter;
    private FragmentManager     mFragmentManager;
    private ArrayList<String>   mGirlsList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        mView = View.inflate(getActivity(), R.layout.fragmet_main_girl, null);


        unbinder = ButterKnife.bind(this, mView);

        initData();
        //        initListener();
        return mView;
    }


    //


    private void initData() {
        mGirlsList = new ArrayList<>();
        ImageUitl.getGirls();
        mFragmentManager = getActivity().getFragmentManager();
        mAdapter = new GirlsAdapter(getActivity());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                                                                            StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

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

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("GirlFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("GirlFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void getGrilsData(List<String> list) {
        LogUtil.d("222222222222222222222222222222222222========================      " + list.size());
    }
}
