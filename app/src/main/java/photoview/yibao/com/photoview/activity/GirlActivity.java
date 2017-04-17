package photoview.yibao.com.photoview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.fragment.PagerViewFragment;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/8 04:24
 */
public class GirlActivity
        extends AppCompatActivity
{
    @BindView(R.id.content_girl_activity)
    FrameLayout mContentGirl;
    private PagerViewFragment mFragment;
    private Bundle mBundle = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_gril);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            mBundle = getIntent().getExtras();

            initData();

        }

    }

    private void initData() {
        if (mFragment == null) {

            mFragment = new PagerViewFragment();
            mFragment.setArguments(mBundle);
            getFragmentManager().beginTransaction()
                                .add(R.id.content_girl_activity, mFragment)
                                .commit();
        }
    }
}
