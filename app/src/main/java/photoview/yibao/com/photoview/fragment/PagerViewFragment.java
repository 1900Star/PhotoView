package photoview.yibao.com.photoview.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import photoview.yibao.com.photoview.MyApplication;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.PagerGirlAdapter;
import photoview.yibao.com.photoview.util.AnimationUtil;
import photoview.yibao.com.photoview.util.BitmapUtil;
import photoview.yibao.com.photoview.util.ImageUitl;
import photoview.yibao.com.photoview.util.NetworkUtil;
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

    //传递过来的position
    private int              mPosition;
    private PagerGirlAdapter mPagerGirlAdapter;
    private List<String> mList = new ArrayList<>();
    private        int      mRgb;
    public static  Activity mActivity;
    @SuppressLint("StaticFieldLeak")
    private static View     mFl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mPosition = arguments.getInt("position");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_pager_view, container, false);
        mFl = view.findViewById(R.id.fl);
        unbinder = ButterKnife.bind(this, view);

        initData();
        return view;
    }

    private void initData() {

        mActivity = getActivity();
        mPagerGirlAdapter = new PagerGirlAdapter(getActivity(), ImageUitl.getUrl(mList));

        mVp.setAdapter(mPagerGirlAdapter);
        mVp.setCurrentItem(mPosition);
        mVp.addOnPageChangeListener(this);

    }

    //图片保存
    @OnClick(R.id.pb_down)
    public void onViewClicked() {

        boolean connected = NetworkUtil.isNetworkConnected(getActivity());
        if (connected) {
            //平移动画
            AnimationUtil.getUpTranslateY(mPbDown);

            SnakbarUtil.showSnakbarLong(mPbDown,
                                        "可以将图片保存起来-_-",
                                        "保存图片",
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                SaveImageUtil.savePic(getActivity().getApplicationContext(),
                                                                      mPosition,
                                                                      mPagerGirlAdapter);
                                            }
                                        })
                       .show();
        } else {

            int color = Color.rgb(255, 64, 129);


            SnakbarUtil.showSnakbarShort(mPbDown, "网络异常，请检查您的网络连接。-_-", color)
                       .show();
        }


    }

    public static ProgressView getProgressView() {

        return new ProgressView(MyApplication.getIntstance());
    }


    /**
     * 根据图片获得主题色
     */
    private int getColor() {
        ImageView       imageView = (ImageView) mPagerGirlAdapter.getPrimaryItem();
        Bitmap          bitmap    = BitmapUtil.drawableToBitmap(imageView.getDrawable());
        Palette.Builder builder   = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if (vibrantSwatch == null) {
                    return;
                }
                mRgb = vibrantSwatch.getRgb();


            }
        });
        return mRgb;
    }


    /**
     * 图片保存成功提示
     */
    public static void showSavePicSuccess() {
        int color = Color.rgb(90, 181, 63);

        SnakbarUtil.showSuccessStatus(mFl, "图片保存成功~", color)
                   .show();

    }

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
        //        getColor();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            getColor();
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("PagerViewFragment"); //统计页面，"MainScreen"为页面名称，可自定义
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("PagerViewFragment");
    }
}
