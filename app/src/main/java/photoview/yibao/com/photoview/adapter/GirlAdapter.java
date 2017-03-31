package photoview.yibao.com.photoview.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import photoview.yibao.com.photoview.Interface.IChangeFragment;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.http.Api;
import photoview.yibao.com.photoview.util.LogUtil;

/**
 * 作者：Stran on 2017/3/29 06:11
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener
{
    @BindView(R.id.pbLoad)
    ProgressBar  mPbLoad;
    @BindView(R.id.tvLoadText)
    TextView     mTvLoadText;
    @BindView(R.id.loadLayout)
    LinearLayout mLoadLayout;
    private View mView;
    private String TAG = "RefreshAdapter";
    private Context mContext;

    private ArrayList<GirlData> mList;

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

    IChangeFragment mIChangeFragment;
    private int mNum;

    public GirlAdapter(Context context, IChangeFragment iChangeFragment) {
        mContext = context;
       this.mIChangeFragment = iChangeFragment;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            mView = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.item_girl, parent, false);
            mView.setOnClickListener(this);
            ViewHolder holder = new ViewHolder(mView);

            return holder;

        } else if (viewType == LOADING_MORE) {
            mView = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.load_more_footview_layout, parent, false);
            LoadMoreViewHolder moreHolder = new LoadMoreViewHolder(mView);
            return moreHolder;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mNum = position;
        send(mNum);
        LogUtil.d("888888888888             === "+mNum);
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            //绑定图片
            Uri url = Uri.parse(Api.picUrlArr[position]);
            viewHolder.mGrilImageView.setImageURI(url);
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
        return Api.picUrlArr == null
               ? 0
               : Api.picUrlArr.length;
    }

    public int getTypeItem(int position) {

        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private void send(int position) {
        LogUtil.d("99999999999999999999     Send===" + position);
        mNum=position;

    }

    public void AddHeader(List<GirlData> items) {
        mList.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooter(List<GirlData> items) {
        mList.addAll(0, items);
        notifyDataSetChanged();


    }

    public void changeMoreStatus(int status) {
        mLoadMoreStatus = status;
        notifyDataSetChanged();

    }


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

    @Override
    public void onClick(View view) {

        mIChangeFragment.change(mNum);
        LogUtil.d("444444444444444444444444444444"+mNum);
    }
    //**************************************************************************************

    static class ViewHolder
            extends RecyclerView.ViewHolder

    {
        @BindView(R.id.gril_image_view)
        SimpleDraweeView mGrilImageView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


    }

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
