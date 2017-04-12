package photoview.yibao.com.photoview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import photoview.yibao.com.photoview.adapter.PagerGirlAdapter;
import photoview.yibao.com.photoview.bean.DownProgress;
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.util.ImageUitl;
import photoview.yibao.com.photoview.util.LogUtil;
import photoview.yibao.com.photoview.util.NetworkUtil;
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
    private PagerGirlAdapter mPagerGirlAdapter;
    private View mView = null;
    private List<String> mList;
    private String mUrl = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault()
                .register(this);
        if (savedInstanceState == null) {

            Bundle arguments = getArguments();
            mPosition = arguments.getInt("position");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState)
    {
        if (savedInstanceState == null) {
            if (mView == null) {

                mView = inflater.inflate(R.layout.fragment_pager_view, container, false);
                unbinder = ButterKnife.bind(this, mView);
                initData();
            }
        }

        return mView;
    }

    private void initData() {
        //        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);
        ImageUitl.getGirls();
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
                //                                WallPaperUtil.setWallPaper(this, mAdapter);
                LogUtil.d("vb jjjjjjj   jj  jj  jj  j   j   jj");
                break;
            case R.id.action_gallery:  //从相册选择壁纸
                //                WallPaperUtil.choiceWallPaper(getActivity());
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 100) //在ui线程执行 优先级100
    public void onGirlDataEvent(GirlData data) {
        mList = data.getList();
        initViewPager(mList);
    }

    private void initViewPager(List<String> list) {
        mPagerGirlAdapter = new PagerGirlAdapter(getActivity(), list);

        mVp.setAdapter(mPagerGirlAdapter);
        mVp.setCurrentItem(mPosition);
        mVp.addOnPageChangeListener(this);
    }

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

    //获取下载进度，设置ProgressBar
    @Subscribe(threadMode = ThreadMode.MAIN,
               priority = 100)
    public void onSetProgress(DownProgress data) {
        int progress = data.getProgress();
        setProgress(progress);
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
        mUrl = mList.get(position);


    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if (state < STATUS_MAX_NUM) {
            mPbDown.setProgress(DEFULT_DOWN_PREGRESS);
        }
    }
}
