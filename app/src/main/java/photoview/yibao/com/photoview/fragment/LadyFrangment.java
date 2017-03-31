package photoview.yibao.com.photoview.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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
import photoview.yibao.com.photoview.adapter.MyPagerAdapter;
import photoview.yibao.com.photoview.base.BaseFragment;
import photoview.yibao.com.photoview.util.LogUtil;
import photoview.yibao.com.photoview.util.SnakbarUtil;
import photoview.yibao.com.photoview.view.ProgressView;

/**
 * 作者：Stran on 2017/3/29 15:21
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class LadyFrangment
        extends BaseFragment
        implements ViewPager.OnPageChangeListener
{
    @BindView(R.id.toolbar)
    Toolbar          mToolbar;
    @BindView(R.id.content)
    ConstraintLayout mContent;
    @BindView(R.id.pb_down_view)
    ProgressView     mPbDownView;
    Unbinder unbinder;
    @BindView(R.id.vp)
    ViewPager mVp;
    private             int  itemPosition      = 0;
    private             long exitTime          = 0;
    /**
     * 闲置状态
     */
    public static final int  SCROLL_STATE_IDLE = 0;


    /**
     * 默认下载进度
     */
    protected static final int DEFULT_DOWN_PREGRESS = 0;
    /**
     * 状态的最值
     */
    public static final    int DEFULT_DOWN_MAX_NUM  = 3;


    private MyPagerAdapter mPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)


    {

        Bundle bundle     = getArguments();
        int position      = (int) bundle.get("arguments");
        LogUtil.d("HHHHHHHHHHHHHHHHHHHHH======      "+position);
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder = ButterKnife.bind(this, rootView);

        initData();

        return rootView;
    }

    private void initData() {
        //        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);

        mPagerAdapter = new MyPagerAdapter(getActivity());
        mVp.setAdapter(mPagerAdapter);
        mVp.addOnPageChangeListener(this);

    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
    }



    @Override
    protected int getLayoutId() {
        return R.layout.app_bar_main;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @OnClick(R.id.vp)
    public void onViewClicked() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        SnakbarUtil.dismissSnakbar(mPbDownView);
    }

    @Override
    public void onPageSelected(int position) {
        mPbDownView.setProgress(position);
        SnakbarUtil.dismissSnakbar(mPbDownView);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state < 3) {
            mPbDownView.setProgress(DEFULT_DOWN_PREGRESS);
            SnakbarUtil.dismissSnakbar(mPbDownView);
        }
    }

    @OnClick({R.id.toolbar,
              R.id.vp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                break;
            case R.id.vp:
                break;
        }
    }
}
