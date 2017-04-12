package photoview.yibao.com.photoview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.GirlsAdapter;
import photoview.yibao.com.photoview.fragment.GirlFragment;
import photoview.yibao.com.photoview.util.SnakbarUtil;


/**
 * 作者：Stran on 2017/3/23 15:12
 * 描述：${}
 * 邮箱：strangermy@outlook.com
 */
public class MainActivity
        extends AppCompatActivity
        implements GirlsAdapter.OnRvItemClickListener
{
    @BindView(R.id.content_activity)
    FrameLayout    mContentActivity;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout   mDrawerLayout;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            initData();
        }

    }


    private void initData() {
        GirlFragment girlFragment = new GirlFragment();
        getFragmentManager().beginTransaction()
                            .add(R.id.content_activity, girlFragment, "one")
                            .commit();
    }

    //接口回调打开ViewPager浏览大图
    @Override
    public void showPagerFragment(int position) {
        Intent intent = new Intent(this, GirlActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        intent.putExtras(bundle);
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
}
