package photoview.yibao.com.photoview.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import photoview.yibao.com.photoview.MyApplication;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.MyPagerAdapter;
import photoview.yibao.com.photoview.util.SaveImageUtil;
import photoview.yibao.com.photoview.util.SnakbarUtil;
import photoview.yibao.com.photoview.util.WallPaperUtil;
import photoview.yibao.com.photoview.view.ProgressView;

/**
 * 作者：Stran on 2017/3/23 15:12
 * 描述：${}
 * 邮箱：strangermy@outlook.com
 */
public class MainActivity1
        extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, View.OnClickListener

{
    private static Context mContext;
    private String TAG = "MainActivity";
    private        ConstraintLayout mLayout;
    private        ViewPager        mVp;
    private        MyPagerAdapter   mAdapter;
    private static ImageView        mFab;
    private        Toolbar          mToolbar;
    public static  ProgressView     mPbDownView;
    private             int  itemPosition      = 0;
    private             long exitTime          = 0;
    /**
     * 闲置状态
     */
    public static final int  SCROLL_STATE_IDLE = 0;


    /**
     * 默认下载进度
     */
    public static final int DEFULT_DOWN_PREGRESS = 0;
    /**
     * 状态的最值
     */
    public static final int STATUS_MAX_NUM       = 3;
    private RelativeLayout mRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mFab.setOnClickListener(this);
    }

    private void initView() {
        mRv = (RelativeLayout) findViewById(R.id.rv);
        mLayout = (ConstraintLayout) findViewById(R.id.content);
        mVp = (ViewPager) mLayout.findViewById(R.id.vp);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mPbDownView = (ProgressView) findViewById(R.id.pb_down_view);
        mFab = (ImageView) findViewById(R.id.fab);
    }

    private void initData() {
        mFab.bringToFront();
        mRv.bringToFront();
        mPbDownView.bringToFront();

        setSupportActionBar(mToolbar);
        mAdapter = new MyPagerAdapter(this);
        mVp.setAdapter(mAdapter);
        mVp.addOnPageChangeListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Settings
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_setwallpaper: //设置壁纸
                //                WallPaperUtil.setWallPaper(this, mAdapter);
                startActivity(new Intent(this, ViewActivty.class));
                break;
            case R.id.action_gallery:  //从相册选择壁纸
                WallPaperUtil.choiceWallPaper(this);
                break;
            case R.id.day:
                MyApplication.getIntstance()
                             .setTheme(this, false);
                break;
            case R.id.night:
                MyApplication.getIntstance()
                             .setTheme(this, true);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *   ViewPager滑动监听
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        SnakbarUtil.dismissSnakbar(mPbDownView);
    }

    @Override
    public void onPageSelected(int position) {
        itemPosition = position;
        SnakbarUtil.dismissSnakbar(mPbDownView);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state < STATUS_MAX_NUM) { //当ViewPager处于闲置状态时，将下载进度重置为0。
            mPbDownView.setProgress(0);
            SnakbarUtil.dismissSnakbar(mPbDownView);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar.make(mFab, "再按一次我就离开了~", Snackbar.LENGTH_LONG)
                        .show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 图片保存成功提示
     */
    public static void showSavePicSuccess() {
        int color = Color.rgb(90, 181, 63);
        SnakbarUtil.showSetSnakBarBagrand(mFab, "图片保存成功~", color)
                   .show();
    }


    public static ProgressView getProgressView() {

        return mPbDownView;
    }


    @Override
    public void onClick(View view) {
//        AnimationUtil.getUpTranslateY(mPbDownView);
        SnakbarUtil.showSnakbarLong(mFab, "可以将图片保存起来-_-", "保存图片", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveImageUtil.savePic(getApplicationContext(), itemPosition, mAdapter, mPbDownView);
            }
        })
                   .show();
    }

    @Override
    protected void onResume() {
        MyApplication.getIntstance()
                     .refreshResources(this);
        super.onResume();
    }
}
