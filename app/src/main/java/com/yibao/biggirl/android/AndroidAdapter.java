package com.yibao.biggirl.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yibao.biggirl.R;
import com.yibao.biggirl.model.android.AndroidAndGirlBean;
import com.yibao.biggirl.model.android.ResultsBeanX;
import com.yibao.biggirl.model.girls.ResultsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author：Sid
 * Des：${适配Android列表数据}
 * Time:2017/4/23 07:08
 */
public class AndroidAdapter
        extends RecyclerView.Adapter<AndroidAdapter.ViewHolder>
{


    private Context                  mContext;
    private List<AndroidAndGirlBean> mList;
    private String mWho = "Smartisan";


    public AndroidAdapter(Context context, List<AndroidAndGirlBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_android_frag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AndroidAndGirlBean item = mList.get(position);


        ResultsBean girlData = item.mGrilData.get(position);
        ResultsBeanX androidData = item.getAndroidData()
                                       .get(position);

        //        holder.mIcon.setImageURI(girlData.getUrl());
        Glide.with(mContext)
             .load(girlData.getUrl())
             .asBitmap()
             .placeholder(R.drawable.splash)
             .diskCacheStrategy(DiskCacheStrategy.SOURCE)
             .into(holder.mIvIcon);

        String who = androidData.getWho();
        String name = who == null
                      ? mWho
                      : who;

        holder.mTvAndroidName.setText(name);

        String time = androidData.getCreatedAt();
        holder.mTvAndroidTime.setText(time.substring(0, time.lastIndexOf("T")));
        holder.mTvAndroidDes.setText(androidData.getDesc());


        holder.itemView.setOnClickListener(view -> {
            if (mContext instanceof OnDesClickListener) {

                ((OnDesClickListener) mContext).showDesDetall(androidData.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null
               ? 0
               : mList.size();
    }


    public interface OnDesClickListener {
        void showDesDetall(String url);

    }


    static class ViewHolder
            extends RecyclerView.ViewHolder
    {
        @BindView(R.id.iv_icon)
        ImageView    mIvIcon;
        @BindView(R.id.tv_android_name)
        TextView     mTvAndroidName;
        @BindView(R.id.tv_android_des)
        TextView     mTvAndroidDes;
        @BindView(R.id.ll)
        LinearLayout mLl;
        @BindView(R.id.tv_android_time)
        TextView     mTvAndroidTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
