package com.yibao.biggirl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.yibao.biggirl.android.AndroidAdapter;
import com.yibao.biggirl.android.AndroidFragment;
import com.yibao.biggirl.girl.GirlActivity;
import com.yibao.biggirl.home.GirlsAdapter;
import com.yibao.biggirl.home.GirlsFragment;
import com.yibao.biggirl.home.TabPagerAdapter;
import com.yibao.biggirl.model.girls.ResultsBean;
import com.yibao.biggirl.util.SnakbarUtil;
import com.yibao.biggirl.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者：Stran on 2017/3/23 15:12
 * 描述：${主页面}
 * 邮箱：strangermy@outlook.com
 */
public class MainActivity
        extends AppCompatActivity
        implements GirlsAdapter.OnRvItemClickListener, AndroidAdapter.OnDesClickListener
{
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout   mDrawerLayout;

    @BindView(R.id.tablayout)
    TabLayout               mTablayout;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.view_pager)
    ViewPager               mViewPager;
    @BindView(R.id.toolbar)
    Toolbar                 mToolbar;
    private long   exitTime   = 0;
    public  String arrTitle[] = {"Girl",
                                 "Android",
                                 "Video"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            initData();
            initView();

        }

    }

    private void initView() {
        setSupportActionBar(mToolbar);

        mTablayout.setupWithViewPager(mViewPager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new GirlsFragment().newInstance());
        fragments.add(new AndroidFragment().newInstance());
        fragments.add(new AndroidFragment().newInstance());
        mViewPager.setOffscreenPageLimit(3);
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(),
                                                           fragments,
                                                           Arrays.asList(arrTitle));
        mViewPager.setAdapter(pagerAdapter);

    }

    private void initData() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                 mDrawerLayout,
                                                                 mToolbar,
                                                                 R.string.navigation_drawer_open,
                                                                 R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //接口回调打开ViewPager浏览大图
    @Override
    public void showPagerFragment(int position, ArrayList<ResultsBean> list) {

        Intent intent = new Intent(this, GirlActivity.class);
        intent.putParcelableArrayListExtra("girlList", list);
        intent.putExtra("position", position);
        startActivity(intent);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            //两秒之内按返回键多次就会退出
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                SnakbarUtil.finishActivity(mDrawerLayout);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void showDesDetall(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);


    }
}
