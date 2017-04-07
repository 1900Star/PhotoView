package photoview.yibao.com.photoview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import photoview.yibao.com.photoview.util.ImageUitl;
import photoview.yibao.com.photoview.view.ZoomImageView;

/**
 * 作者：Stran on 2017/3/23 03:31
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class PagerGirlAdapter
        extends android.support.v4.view.PagerAdapter
{
    private Context      mContext;
    private View         mCurrentView;
    private List<String> mList;


    public PagerGirlAdapter(Context context, List<String> list) {

        this.mContext = context;
        this.mList = list;
    }


    @Override
    public int getCount() {
        return mList == null
               ? 0
               : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ZoomImageView view = new ZoomImageView(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                                   ViewGroup.LayoutParams.MATCH_PARENT);

        view.setScaleType(ImageView.ScaleType.MATRIX);
        view.reSetState();
        //加载图片
        ImageUitl.glideLoadPic(mContext, mList.get(position), view);

        container.addView(view, params);

        return view;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View) object;


    }

    public View getPrimaryItem() {

        return mCurrentView;
    }
}
