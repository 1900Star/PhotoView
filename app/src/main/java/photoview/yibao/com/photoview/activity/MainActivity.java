package photoview.yibao.com.photoview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import photoview.yibao.com.photoview.MyApplication;
=======
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
>>>>>>> dev
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.GirlsAdapter;
import photoview.yibao.com.photoview.fragment.GirlFragment;
import photoview.yibao.com.photoview.fragment.PagerViewFragment;
import photoview.yibao.com.photoview.util.WallPaperUtil;


/**
 * 作者：Stran on 2017/3/23 15:12
 * 描述：${}
 * 邮箱：strangermy@outlook.com
 */
public class MainActivity
        extends AppCompatActivity
        implements GirlsAdapter.OnRvItemClickListener,
                   NavigationView.OnNavigationItemSelectedListener


{
<<<<<<< HEAD
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
=======
    @BindView(R.id.content_activity)
    FrameLayout    mContentActivity;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout   mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar        mToolbar;


    private long exitTime = 0;
    private PagerViewFragment mPagerViewFragment;
    private GirlFragment      mGirlFragment;
>>>>>>> dev

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mFab.setOnClickListener(this);
=======
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            initView();
            initData();
        }

>>>>>>> dev
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Smartisan T1");
        mToolbar.setNavigationIcon(R.mipmap.google);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                 mDrawerLayout,
                                                                 mToolbar,
                                                                 R.string.navigation_drawer_open,
                                                                 R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);

    }

<<<<<<< HEAD
    private void initData() {
        mFab.bringToFront();
        mRv.bringToFront();
        mPbDownView.bringToFront();

        setSupportActionBar(mToolbar);
        mAdapter = new MyPagerAdapter(this);
        mVp.setAdapter(mAdapter);
        mVp.addOnPageChangeListener(this);
    }

=======

    private void initData() {
        mGirlFragment = new GirlFragment();
        getFragmentManager().beginTransaction()
                            .add(R.id.content_activity, mGirlFragment, "one")
                            .commit();
    }

    //接口回调打开ViewPager浏览大图
    @Override
    public void showPagerFragment(int position) {
        Intent intent = new Intent(this, GirlActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        intent.putExtras(bundle);
        startActivity(intent);
        //        mToolbar.setTitle("Google");

        //        mToolbar.setVisibility(View.GONE);
//        if (mPagerViewFragment == null) {
//            mPagerViewFragment = new PagerViewFragment();
//        }
//        Bundle bundle = new Bundle();
//        bundle.putInt("position", position);
//        mPagerViewFragment.setArguments(bundle);
//        getFragmentManager().beginTransaction()
//                            .hide(mGirlFragment)
//                            .add(R.id.content_activity, mPagerViewFragment, "two")
//                            .addToBackStack(null)
//                            .commit();


    }


>>>>>>> dev
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.inflateMenu(R.menu.main);
        return true;
    }

<<<<<<< HEAD
    //Settings
=======
>>>>>>> dev
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
<<<<<<< HEAD
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
=======

            case R.id.action_gallery:  //从相册选择壁纸
//                WallPaperUtil.choiceWallPaper(this);
//                startActivity();

>>>>>>> dev
                break;
        }
        return super.onOptionsItemSelected(item);
    }

<<<<<<< HEAD
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

=======
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_setwallpar: //设置壁纸
                //                                WallPaperUtil.setWallPaper(this, mAdapter);
                break;
            case R.id.nav_gallery:  //从相册选择壁纸
                WallPaperUtil.choiceWallPaper(this);

                break;
            case R.id.nav_day:
                //                MyApplication.getIntstance()
                //                             .setTheme(this, false);
                break;
            case R.id.nav_night:
                //                MyApplication.getIntstance()
                //                             .setTheme(this, true);
                break;
            default:
                break;
        }
>>>>>>> dev


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


<<<<<<< HEAD
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
=======
>>>>>>> dev
}
