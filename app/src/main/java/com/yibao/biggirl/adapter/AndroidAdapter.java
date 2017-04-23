package com.yibao.biggirl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yibao.biggirl.R;
import com.yibao.biggirl.bean.android.ResultsBeanX;

import java.util.List;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/23 07:08
 */
public class AndroidAdapter
        extends RecyclerView.Adapter<AndroidAdapter.AndroidHolder>
{


    private Context            mContext;
    private List<ResultsBeanX> mList;

    public AndroidAdapter(Context context, List<ResultsBeanX> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public AndroidHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_android_frag, parent, false);


        return new AndroidHolder(view);
    }

    @Override
    public void onBindViewHolder(AndroidHolder holder, int position) {
        ResultsBeanX ganKio = mList.get(position);
        holder.itemView.setOnClickListener(view -> {
            if (mContext instanceof OnDesClickListener) {
                ((OnDesClickListener)mContext).showDesDetall(ganKio.getUrl());
            }
        });


//        holder.mIcon.setImageURI(Api.picUrlArr[position]);
        holder.mAndroidTvName.setText(ganKio.getWho());
        String time = ganKio.getCreatedAt();
        holder.mAndroidTvTima.setText(time.substring(0, time.lastIndexOf("T")));
        holder.mAndroidTvDes.setText(ganKio.getDesc());
    }


    @Override
    public int getItemCount() {
        return mList == null
               ? 0
               : mList.size();
    }

    static class AndroidHolder
            extends RecyclerView.ViewHolder
    {


        private final TextView         mAndroidTvName;
        private final TextView         mAndroidTvDes;
        private final TextView         mAndroidTvTima;
        private final SimpleDraweeView mIcon;

        AndroidHolder(View itemView) {
            super(itemView);
            mIcon = (SimpleDraweeView) itemView.findViewById(R.id.iv_icon);
            mAndroidTvName = (TextView) itemView.findViewById(R.id.tv_android_name);
            mAndroidTvDes = (TextView) itemView.findViewById(R.id.tv_android_des);
            mAndroidTvTima = (TextView) itemView.findViewById(R.id.tv_android_time);


        }
    }

    public interface OnDesClickListener{
        void showDesDetall(String url);

    }

}
