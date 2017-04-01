package photoview.yibao.com.photoview.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.fragment.PagerViewFragment;

/**
 * 作者：Stran on 2017/3/27 18:58
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */

public class ViewActivty
        extends AppCompatActivity

{


    @BindView(R.id.activity_content)
    FrameLayout mIdContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        ButterKnife.bind(this);
        PagerViewFragment
                fragment = new PagerViewFragment();
        FragmentManager     fm       = getFragmentManager();
        FragmentTransaction ft       = fm.beginTransaction();
        ft.add(R.id.activity_content, fragment);
        ft.commit();


    }
}



