package photoview.yibao.com.photoview.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.fragment.GirlFragment;
import photoview.yibao.com.photoview.util.LogUtil;

/**
 * 作者：Stran on 2017/3/27 18:58
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */

public class ViewActivty
        extends AppCompatActivity


{


    @BindView(R.id.activity_content)
    FrameLayout mActivityContent;
    private FragmentManager mManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);

        Intent          intent   = getIntent();
        String          position = intent.getStringExtra("position");
        mManager = getFragmentManager();
        LogUtil.d("position           ========  " + position);
        ButterKnife.bind(this);
        GirlFragment fragment = new GirlFragment();

        mManager.beginTransaction()
                            .add(R.id.activity_content, fragment)
                            .addToBackStack(null)
                            .commit();


    }
}



