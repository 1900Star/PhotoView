package photoview.yibao.com.photoview.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import photoview.yibao.com.photoview.Interface.IChangeFragment;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.adapter.GirlAdapter;
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.util.LogUtil;
import photoview.yibao.com.photoview.util.WallPaperUtil;

/**
 * 作者：Stran on 2017/3/29 01:18
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlFragment
        extends Fragment
        implements View.OnClickListener
{


    public View mView;


    @BindView(R.id.fragment_girl_recycler)
    RecyclerView       mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    Unbinder unbinder;


    private ArrayList<GirlData> mList;
    private AppCompatActivity   mActivity;
    private IChangeFragment mIChangeFragment=new IChangeFragment() {
        @Override
        public void change(int position) {
            LogUtil.d("fffffffff            =="+position);
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        mView = View.inflate(getActivity(), R.layout.fragmet_girl, null);


        unbinder = ButterKnife.bind(this, mView);
        initData();
        initListener();
        return mView;
    }

    private void initListener() {
           mRecyclerView.setOnClickListener(this);


    }


    private void initData() {
       getActivity().getFragmentManager();


        GirlAdapter         adapter  = new GirlAdapter(getActivity(),mIChangeFragment);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        StaggeredGridLayoutManager manager1 = new StaggeredGridLayoutManager(2,
                                                                            StaggeredGridLayoutManager.VERTICAL);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
//
        //        mRv.setAd
        mRecyclerView.setAdapter(adapter);
    }

    private void initToobar() {

        mActivity = ((AppCompatActivity) getActivity());
        Toolbar toobar = (Toolbar) mView.findViewById(R.id.toolbarr);
        mActivity.setSupportActionBar(toobar);
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }

    }


    @OnClick({R.id.fragment_girl_recycler,
              R.id.swipe_refresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_girl_recycler:
                break;
            case R.id.swipe_refresh:
                break;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);

        super.onCreateOptionsMenu(menu, inflater);
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
                WallPaperUtil.choiceWallPaper(getActivity());
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View view) {
        LogUtil.d("777777777777777777777777777777777777");
    }
}
