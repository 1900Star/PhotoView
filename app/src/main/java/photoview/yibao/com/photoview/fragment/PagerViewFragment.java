package photoview.yibao.com.photoview.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.PagerViewAdapter;
import photoview.yibao.com.photoview.util.LogUtil;
import photoview.yibao.com.photoview.util.NetWorkUtils;
import photoview.yibao.com.photoview.util.SaveImageUtil;
import photoview.yibao.com.photoview.util.SnakbarUtil;
import photoview.yibao.com.photoview.view.ProgressView;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/1 05:29
 */
public class PagerViewFragment
        extends Fragment
        implements ViewPager.OnPageChangeListener
{
    @BindView(R.id.toolbar)
    Toolbar      mToolbar;
    @BindView(R.id.vp)
    ViewPager    mVp;
    @BindView(R.id.pb_down)
    ProgressView mPbDown;
    Unbinder unbinder;


    private int mPosition;
    private boolean isFirst = true;
    private PagerViewAdapter mPagerAdapter;
    private int              mMCrurrentPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mPosition = arguments.getInt("position");
        LogUtil.d("this is cureent position        " + mPosition);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_pager_view, container, false);

        unbinder = ButterKnife.bind(this, view);

        initData();
        return view;
    }

    private void initData() {
        if (isFirst) {
            mPagerAdapter = new PagerViewAdapter(getActivity(), mPosition);
            isFirst = false;
        } else {
            LogUtil.d("555555555555555555555555555555555555555");
            mPagerAdapter = new PagerViewAdapter(getActivity(), mMCrurrentPosition);
        }
        mVp.setAdapter(mPagerAdapter);
        mVp.addOnPageChangeListener(this);

    }

    //图片保存
    @OnClick(R.id.pb_down)
    public void onViewClicked() {

        boolean connected = NetWorkUtils.isConnected();
        LogUtil.d("11111" + connected);
        if (connected) {
            //平移动画
//            AnimationUtil.getUpTranslateY(mPbDown);

            SnakbarUtil.showSnakbarLong(mPbDown,
                                        "可以将图片保存起来-_-",
                                        "保存图片",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                SaveImageUtil.savePic(getActivity().getApplicationContext(),
                                                                      mPosition,
                                                                      mPagerAdapter);
                                            }
                                        })
                       .show();
        } else {

            int color = Color.rgb(255, 64, 129);


            SnakbarUtil.showSnakbarShort(mPbDown, "网络异常，请检查您的网络连接。-_-", color)
                       .show();
        }


    }

    /**
     * 图片保存成功提示
     */
//    public void showSavePicSuccess() {
//        int color = Color.rgb(90, 181, 63);
//
//        SnakbarUtil.showSuccessStatus(mPbDown, "图片保存成功~", color)
//                   .show();
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mMCrurrentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
