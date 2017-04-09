package photoview.yibao.com.photoview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
<<<<<<< HEAD:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlAdapter.java
import android.net.Uri;
import android.app.FragmentManager;
import android.os.Bundle;
=======
>>>>>>> dev:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlsAdapter.java
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import photoview.yibao.com.photoview.R;
<<<<<<< HEAD:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlAdapter.java
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.fragment.LadyFrangment;
import photoview.yibao.com.photoview.http.Api;
import photoview.yibao.com.photoview.util.LogUtil;
=======
>>>>>>> dev:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlsAdapter.java

/**
 * 作者：Stran on 2017/3/29 06:11
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlsAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>


{
    @BindView(R.id.pbLoad)
    ProgressBar      mPbLoad;
    @BindView(R.id.tvLoadText)
    TextView         mTvLoadText;
    @BindView(R.id.loadLayout)
    LinearLayout     mLoadLayout;
    @BindView(R.id.gril_image_view)
    SimpleDraweeView mGrilImageView;

    private String TAG = "RefreshAdapter";
    @SuppressLint("StaticFieldLeak")
    private static Context         mContext;
    private static FragmentManager mManager;

    private List<String> mList;

    private static final int TYPE_ITEM   = 0;
    private static final int TYPE_FOOTER = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE     = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE     = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;
<<<<<<< HEAD:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlAdapter.java
    private static int mCurrentItem;

    public GirlAdapter(Context context, FragmentManager manager) {
        mManager = manager;
        mContext = context;


=======

    private int mNum;


    //回调接口
    public OnRvItemClickListener mItemClickListener;

    public interface OnRvItemClickListener {
        void showPagerFragment(int position);

    }

    public void setShowPagerViewListener(OnRvItemClickListener listener) {
        this.mItemClickListener = listener;
    }


    public GirlsAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        //       this.mIChangeFragment = iChangeFragment;
>>>>>>> dev:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlsAdapter.java
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
<<<<<<< HEAD:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlAdapter.java
            View mView = LayoutInflater.from(parent.getContext())
                                       .inflate(R.layout.item_girl, parent, false);
=======
            mView = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_girl, parent, false);
>>>>>>> dev:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlsAdapter.java

            return new ViewHolder(mView);

        } else if (viewType == LOADING_MORE) {
<<<<<<< HEAD:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlAdapter.java
            View mView = LayoutInflater.from(parent.getContext())
                                       .inflate(R.layout.load_more_footview_layout, parent, false);

=======
            mView = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.load_more_footview_layout, parent, false);
>>>>>>> dev:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlsAdapter.java
            return new LoadMoreViewHolder(mView);

        }
        return null;
    }

    //绑定视图
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
<<<<<<< HEAD:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlAdapter.java
            ViewHolder viewHolder = (ViewHolder) holder;
            mCurrentItem = position;
            //绑定图片
            Uri url = Uri.parse(Api.picUrlArr[position]);

            viewHolder.mGrilImageView.setImageURI(url);

=======
            final ViewHolder viewHolder = (ViewHolder) holder;
            //设置监听
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //打开PagerView的回调
                    if (mContext instanceof OnRvItemClickListener) {
                        ((OnRvItemClickListener) mContext).showPagerFragment(position);
                    }
                }
            });
            //绑定图片
//            Uri url = Uri.parse(Api.picUrlArr[position]);

            viewHolder.mGrilImageView.setImageURI(mList.get(position));
>>>>>>> dev:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlsAdapter.java
        } else if (holder instanceof LoadMoreViewHolder) {
            LoadMoreViewHolder moreViewHolder = (LoadMoreViewHolder) holder;
            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    if (mList.size() == 0) {
                        moreViewHolder.mLoadLayout.setVisibility(View.GONE);
                    }
                    moreViewHolder.mTvLoadText.setText("上拉加载更多");
                    break;
                case LOADING_MORE:
                    moreViewHolder.mTvLoadText.setText("正在加载更多");
                    break;
                case NO_LOAD_MORE:
                    moreViewHolder.mLoadLayout.setVisibility(View.GONE);
                default:
                    break;
            }
        }


    }

    @Override
    public int getItemCount() {
        return mList == null
               ? 0
               : mList.size();
    }

    public int getTypeItem(int position) {

        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }


<<<<<<< HEAD:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlAdapter.java
    //列表的Holder
    static class ViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener
=======
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pbLoad:
                break;
            case R.id.tvLoadText:
                break;
            case R.id.loadLayout:
                break;
        }
    }


    //**************************************************************************************

    static class ViewHolder
            extends RecyclerView.ViewHolder

>>>>>>> dev:app/src/main/java/photoview/yibao/com/photoview/adapter/GirlsAdapter.java
    {
        private static final String ARGUMENTS = "arguments";
        @BindView(R.id.gril_image_view)
        SimpleDraweeView mGrilImageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mGrilImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            LadyFrangment frangment=new LadyFrangment();
            Bundle bundle = new Bundle();
            bundle.putInt(ARGUMENTS, mCurrentItem);

            frangment.setArguments(bundle);
            LogUtil.d("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
            mManager.beginTransaction()
                    .add(R.id.activity_content, frangment)
                    .addToBackStack(null)
                    .commit();
            //            Intent intent = new Intent(mContext, ViewActivty.class);
            //            intent.putExtra("position", mCurrentItem);
            //          mContext.startActivity(intent);


        }


    }

    //加载更多的Holder
    static class LoadMoreViewHolder
            extends RecyclerView.ViewHolder
    {
        @BindView(R.id.pbLoad)
        ProgressBar  mPbLoad;
        @BindView(R.id.tvLoadText)
        TextView     mTvLoadText;
        @BindView(R.id.loadLayout)
        LinearLayout mLoadLayout;

        LoadMoreViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);

        }
    }

}
