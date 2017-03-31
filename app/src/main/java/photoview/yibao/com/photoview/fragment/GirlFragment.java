package photoview.yibao.com.photoview.fragment;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.GirlAdapter;
import photoview.yibao.com.photoview.base.BaseFragment;
import photoview.yibao.com.photoview.util.LogUtil;

/**
 * 作者：Stran on 2017/3/29 01:18
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlFragment
        extends BaseFragment
        implements View.OnClickListener, RecyclerView.OnItemTouchListener


{


    @BindView(R.id.fragment_girl_recycler)
    RecyclerView       mRv;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;
    private Handler handler = new Handler();
    private GirlAdapter     mAdapter;
    private FragmentManager mManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragmet_girl;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        initData();
    }

    @Override
    protected void initListener() {
       mRv.addOnItemTouchListener(this);

    }


    private void initData() {
        mManager=getActivity().getFragmentManager();
        mSwipeRefresh.setRefreshing(false);
        mSwipeRefresh.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW);

        mAdapter = new GirlAdapter(getActivity(),mManager);
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                                                                            StaggeredGridLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);
        mRv.setHasFixedSize(true);
        mRv.setItemAnimator(new DefaultItemAnimator());
        mRv.setAdapter(mAdapter);

    }


    private void loadMoreDatat() {
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastItem + 1 == mAdapter.getItemCount()) {
                    boolean isRefresh = mSwipeRefresh.isRefreshing();
                    if (isRefresh) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    } else if (!isRefresh) {
                        mAdapter.changeMoreStatus(mAdapter.LOADING_MORE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                //                                mAdapter.AddFooter(m);

                            }
                        }, 1000);
                    }

                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {

        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                LogUtil.d("AAAAAAAAAA====");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:

            default:
                break;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                LogUtil.d("BBBBBBBBBBBB====");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:

            default:
                break;
        }



    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        LogUtil.d("CCCCCCCCCCCCCCCCCCCC=="+disallowIntercept);
    }

    @Override
    public void onClick(View view) {
        LogUtil.d("DDDDDDDDDDDDDDDDDDDDDDDD");
    }
}
