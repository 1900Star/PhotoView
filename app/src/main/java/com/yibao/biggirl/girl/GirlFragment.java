package com.yibao.biggirl.girl;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.yibao.biggirl.R;
import com.yibao.biggirl.model.girl.DownGrilProgressData;
import com.yibao.biggirl.model.girl.GirlData;
import com.yibao.biggirl.model.girls.ResultsBean;
import com.yibao.biggirl.util.LogUtil;
import com.yibao.biggirl.util.NetworkUtil;
import com.yibao.biggirl.util.SnakbarUtil;
import com.yibao.biggirl.util.WallPaperUtil;
import com.yibao.biggirl.view.ProgressView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/1 05:29
 */
public class GirlFragment
        extends Fragment
        implements ViewPager.OnPageChangeListener
{

    @BindView(R.id.vp)
    ViewPager    mVp;
    @BindView(R.id.iv_down)
    ProgressView mPbDown;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    //传递过来的position
    private int mPosition;
    //ViewPger当前显示的图片
    //默认下载进度
    public static final int DEFULT_DOWN_PREGRESS = 0;
    //下载进度最大值
    public static       int MAX_DOWN_PREGRESS    = 100;

    //PerView滑动的状态的最值
    public static final int STATUS_MAX_NUM = 3;
    private GirlAdapter mPagerGirlAdapter;
    private View    mView          = null;
    private String  mUrl           = null;
    private boolean isShowGankGirl = true;
    private ArrayList<ResultsBean> mList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault()
                .register(this);
        Bundle bundle = getArguments();
        mList = bundle.getParcelableArrayList("girlList");
        mPosition = bundle.getInt("position");
        LogUtil.d("Position  Size    ==   " + mPosition + "===" + mList.size());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (savedInstanceState == null) {

            mView = inflater.inflate(R.layout.girl_frag, container, false);
            unbinder = ButterKnife.bind(this, mView);
            initData();

        }

        return mView;
    }

    //获取下载进度，设置ProgressBar
    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 100)
    public void onProgressEvent(DownGrilProgressData data) {
        setProgress(data.getProgress());
    }

    //获取下载进度，设置ProgressBar
    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 100)
    public void onGetDataEvent(GirlData data) {


    }

    private void initData() {
        setHasOptionsMenu(true);
        mPagerGirlAdapter = new GirlAdapter(getActivity(), mList);
        mVp.setAdapter(mPagerGirlAdapter);
        mVp.setCurrentItem(mPosition);
        mVp.addOnPageChangeListener(this);

    }

  /*  private void getDefultGirl() {
        isShowGankGirl = true;
         mList=ImageUitl.getDefultUrl(ImageUitl.getDefultUrl(new ArrayList<>()));
        mPagerGirlAdapter = new GirlAdapter(getActivity(), null);

        mVp.setAdapter(mPagerGirlAdapter);
        mPagerGirlAdapter.notifyDataSetChanged();
    }*/

    //图片保存
    @OnClick(R.id.iv_down)
    public void onViewClicked() {

        //网络检查
        boolean connected = NetworkUtil.isNetworkConnected(getActivity());

        if (connected) {
            SnakbarUtil.savePic(getActivity().getApplicationContext(),
                                mPbDown,
                                mUrl,
                                mPagerGirlAdapter);

        } else {
            SnakbarUtil.netErrors(mPbDown);
        }
    }

    private void setProgress(int progress) {
        mPbDown.setProgress(progress);

        if (progress == MAX_DOWN_PREGRESS) {
            SnakbarUtil.showSuccessStatus(mPbDown);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault()
                .unregister(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (isShowGankGirl) {
        }
        mUrl = mList.get(position)
                    .getUrl();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if (state < STATUS_MAX_NUM) {
            mPbDown.setProgress(DEFULT_DOWN_PREGRESS);

        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setwallpaer: //设置壁纸
                WallPaperUtil.setWallPaper(getActivity(), mPagerGirlAdapter);
                SnakbarUtil.setWallpaer(mPbDown);
                break;
            case R.id.action_gallery:  //从相册选择壁纸
                WallPaperUtil.choiceWallPaper(getActivity());
                break;
            case R.id.action_localgirl:  //默认美女
                //                getDefultGirl();
                break;
            case R.id.action_gank:  //干货集中营
                initData();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    public GirlFragment newInstance() {


        return new GirlFragment();
    }

}
