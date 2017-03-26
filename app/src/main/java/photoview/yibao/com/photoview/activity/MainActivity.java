package photoview.yibao.com.photoview.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.view.WindowManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import photoview.yibao.com.photoview.MyApplication;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.MyPagerAdapter;
import photoview.yibao.com.photoview.bean.GirlBean;
import photoview.yibao.com.photoview.bean.ResultsBean;
import photoview.yibao.com.photoview.bean.Woman;
import photoview.yibao.com.photoview.util.Constans;
import photoview.yibao.com.photoview.util.GirlUitl;
import photoview.yibao.com.photoview.util.LogUtil;
import photoview.yibao.com.photoview.util.SavePic;

/**
 * 作者：Stran on 2017/3/23 15:12
 * 描述：${}
 * 邮箱：strangermy@outlook.com
 */
public class MainActivity
        extends AppCompatActivity
        implements ViewPager.OnPageChangeListener

{
    private static Context mContext;
    private String TAG      = "MainActivity";
    private long   exitTime = 0;
    private ConstraintLayout mLayout;
    private ViewPager        mVp;
    private MyPagerAdapter   mAdapter;
    private int itemPosition = 0;
    private static FloatingActionButton mFab;
    static         List<ResultsBean>    mResults;
    static String url = Constans.BASE_URL + "/1000/1";
    private Toolbar mToolbar;
    private boolean isLoadiing = false;
    private Woman mGirlBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        mGirlBean = (Woman) intent.getSerializableExtra("GirlBean");


        initView();


        initData();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "可以将图片保存起来-_-", 5000)
                        .setAction("保存图片", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                SavePic.savePic(getApplicationContext(), itemPosition, mAdapter);
                            }
                        })
                        .show();
            }
        });


    }

    private void initView() {
        mLayout = (ConstraintLayout) findViewById(R.id.content);
        mVp = (ViewPager) mLayout.findViewById(R.id.vp);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);


    }

    private void initData() {

        setSupportActionBar(mToolbar);
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
            GirlUitl.get()
                    .initGirlData();
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
    }

    /**
     * 保存图片
     */
    public static List<ResultsBean> initGirlData()
    {

        LogUtil.d("进入下载方法", "////////////////////////////////////////////");
        Request request = new Request.Builder().url(url)
                                               .build();
        MyApplication.defaultOkHttpClient()
                     .newCall(request)
                     .enqueue(new Callback() {
                         @Override
                         public void onFailure(Call call, IOException e) {
                             //下载失败
                             //                       listener.onDownloadFailed();
                         }

                         @Override
                         public void onResponse(Call call, Response response)
                                 throws IOException
                         {
                             String json = response.body()
                                                   .string();

                             //                       LogUtil.d(
                             //                               "================Girl 哈哈 =="+json);

                             Gson     gson     = new Gson();
                             GirlBean girlData = gson.fromJson(json, GirlBean.class);
                             mResults = girlData.getResults();

                             ResultsBean resultsBean = mResults.get(460);

                             String ganhuo_id = resultsBean.getUrl();
                             LogUtil.d("__-----++=++++++++++这是图片的 长度=====Url ==" + mResults.size());
                             LogUtil.d("__-----++=++++++++++这是图片的 =====Url ==" + ganhuo_id);


                         }
                     });

        return mResults;
    }

}
