package com.yibao.biggirl.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yibao.biggirl.R;
import com.yibao.biggirl.model.girls.ResultsBean;
import com.yibao.biggirl.util.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 作者：Stran on 2017/3/29 06:11
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlsAdapter
        extends RecyclerView.Adapter<GirlsAdapter.ViewHolder>


{


    private String TAG = "RefreshAdapter";
    @SuppressLint("StaticFieldLeak")
    private Context mContext;

    //    private List<String> mList;
    private List<ResultsBean> mList;

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

    private int mNum;


    //回调接口

    public interface OnRvItemClickListener {
        void showPagerFragment(int position, List<ResultsBean> list);

    }


    public GirlsAdapter(Context context, List<ResultsBean> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public GirlsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_girls, parent, false);

        return new ViewHolder(view);
        //        if (viewType == TYPE_ITEM) {
        //
        //        } else if (viewType == LOADING_MORE) {
        //            view = LayoutInflater.from(parent.getContext())
        //                                 .inflate(R.layout.load_more_footview_layout, parent, false);
        //            return new LoadMoreViewHolder(view);
        //
        //        }
    }

    //绑定视图
    @Override
    public void onBindViewHolder(GirlsAdapter.ViewHolder holder, final int position) {
//        if (holder instanceof ViewHolder) {
            //            EventBus.getDefault()
            //                    .post(position);


//            final ViewHolder viewHolder = (ViewHolder) holder;
            String url = mList.get(position)
                              .getUrl();
            //绑定图片
            Glide.with(mContext)
                 .load(url)
                 .asBitmap()
                 .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                 .into(holder.mGrilImageView);
            //设置监听
            holder.itemView.setOnClickListener(view -> {

                //打开PagerView的回调
                if (mContext instanceof OnRvItemClickListener) {

                    LogUtil.d("nanannananna  === " + position);
                    //                    ((OnRvItemClickListener) mContext).showPagerFragment(position, mList);
                }
            });

//        } else if (holder instanceof LoadMoreViewHolder) {
//            LoadMoreViewHolder moreViewHolder = (LoadMoreViewHolder) holder;
//            switch (mLoadMoreStatus) {
//                case PULLUP_LOAD_MORE:
//                    if (mList.size() == 0) {
//                        moreViewHolder.mLoadLayout.setVisibility(View.GONE);
//                    }
//                    moreViewHolder.mTvLoadText.setText("上拉加载更多");
//                    break;
//                case LOADING_MORE:
//                    moreViewHolder.mTvLoadText.setText("正在加载更多");
//                    break;
//                case NO_LOAD_MORE:
//                    moreViewHolder.mLoadLayout.setVisibility(View.GONE);
//                default:
//                    break;
//            }
//        }


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


    //**************************************************************************************

    static class ViewHolder
            extends RecyclerView.ViewHolder

    {
        @BindView(R.id.gril_image_view)
        ImageView mGrilImageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
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
