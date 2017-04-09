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
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            initView();
            initData();
        }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.inflateMenu(R.menu.main);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.action_gallery:  //从相册选择壁纸
//                WallPaperUtil.choiceWallPaper(this);
//                startActivity();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

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


        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


}
