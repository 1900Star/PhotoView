package photoview.yibao.com.photoview.activity;

import android.graphics.Color;
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
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import photoview.yibao.com.photoview.MyApplication;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.GirlAdapter;
import photoview.yibao.com.photoview.fragment.GirlFragment;
import photoview.yibao.com.photoview.fragment.PagerViewFragment;
import photoview.yibao.com.photoview.util.SnakbarUtil;
import photoview.yibao.com.photoview.util.WallPaperUtil;
import photoview.yibao.com.photoview.view.ProgressView;


/**
 * 作者：Stran on 2017/3/23 15:12
 * 描述：${}
 * 邮箱：strangermy@outlook.com
 */
public class MainActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   GirlAdapter.OnRvItemClickListener


{
    @BindView(R.id.content_activity)
    FrameLayout    mContentActivity;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout   mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar        mToolbar;


    private long exitTime = 0;
    private        PagerViewFragment mPagerViewFragment;
//    @SuppressLint("StaticFieldLeak")
    private static View mV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mV = findViewById(R.id.view);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            initView();
            initData();
        }

    }

    private void initView() {
        mToolbar.setTitle("Smartisan T1");
        mToolbar.setNavigationIcon(R.mipmap.google);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                 mDrawerLayout,
                                                                 mToolbar,
                                                                 R.string.navigation_drawer_open,
                                                                 R.string.navigation_drawer_close);
        //        mDrawerLayout.setDrawerListener(toggle);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mNavView.setNavigationItemSelectedListener(this);

    }

    private void initData() {
        mNavView.setNavigationItemSelectedListener(this);
        getFragmentManager().beginTransaction()
                            .add(R.id.content_activity, new GirlFragment(), "one")
                            .commit();
    }

    //打开ViewPager浏览大图
    @Override
    public void showPagerFragment(int position) {
        //        mToolbar.setTitle("Google");
        if (mPagerViewFragment == null) {
            mPagerViewFragment = new PagerViewFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        mPagerViewFragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                            .replace(R.id.content_activity, mPagerViewFragment, "two")
                            .addToBackStack(null)
                            .commit();


    }

    /**
     * 图片保存成功提示
     */
    public static void showSavePicSuccess() {
        int       color     = Color.rgb(90, 181, 63);
        SnakbarUtil.showSuccessStatus(mV, "图片保存成功~", color)
                   .show();
//        LogUtil.d("success=======================================================");
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {

    }


    public static ProgressView getProgressView() {

        return new ProgressView(MyApplication.getIntstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.inflateMenu(R.menu.main);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_setwallpaper: //设置壁纸
                //                WallPaperUtil.setWallPaper(this, mAdapter);
                //                startActivity(new Intent(this, ViewActivty.class));
                break;
            case R.id.action_gallery:  //从相册选择壁纸
                WallPaperUtil.choiceWallPaper(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
