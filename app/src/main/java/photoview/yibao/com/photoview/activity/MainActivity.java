package photoview.yibao.com.photoview.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.MyPagerAdapter;
import photoview.yibao.com.photoview.util.DownPic;

public class MainActivity
        extends AppCompatActivity
        implements ViewPager.OnPageChangeListener

{
    private String TAG      = "MainActivity";
    private long   exitTime = 0;
    private ConstraintLayout mLayout;
    private ViewPager        mVp;
    private MyPagerAdapter   mAdapter;
    private int itemPosition = 0;
    private Handler mHandler;
    int DOWNLOAD_FAILED   = 0;
    int DOWNLOAD_SUCCESS  = 1;
    int DOWNLOAD_PROGRASS = 2;
    private static FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mLayout = (ConstraintLayout) findViewById(R.id.content);
        mVp = (ViewPager) mLayout.findViewById(R.id.vp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);

        initData();
        setSupportActionBar(toolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "可以将图片保存起来-_-", 5000)
                        .setAction("保存图片", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DownPic.getPic(getApplicationContext(), itemPosition, mAdapter);


                            }
                        })
                        .show();
            }
        });


    }

    private void initData() {
        mAdapter = new MyPagerAdapter(getApplicationContext());
        mVp.setAdapter(mAdapter);
        mVp.addOnPageChangeListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
//            showSavePicSuccess();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        itemPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键多次就会退出
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

    public static void showSavePicSuccess() {
        Snackbar.make(mFab, "图片保存成功~", Snackbar.LENGTH_LONG)
                .show();
    }


}
