package photoview.yibao.com.photoview.util;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

import photoview.yibao.com.photoview.adapter.PagerGirlAdapter;


/**
 * 作者：Stran on 2017/3/28 01:31
 * 描述：${}
 * 邮箱：strangermy@outlook.com
 */
public class SnakbarUtil {
    /**
     * 下载成功提示
     */
    public static Snackbar showSuccessStatus(View view, String message) {
        int      color    = Color.rgb(90, 181, 63);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.getView()
                .setBackgroundColor(color);

        return snackbar;
    }

    /**
     * 保存图片提示
     */
    public static Snackbar showSnakbarLong(final View view,
                                           String message,
                                           String action,
                                           View.OnClickListener listener)
    {
        int color = Color.argb(255, 239, 152, 59);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                                    .addCallback(new Snackbar.Callback() {
                                        @Override
                                        public void onShown(Snackbar sb) {
                                            super.onShown(sb);
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {

                                                }
                                            });

                                        }

                                        @Override
                                        public void onDismissed(Snackbar transientBottomBar,
                                                                int event)
                                        {
<<<<<<< HEAD
//                                                                                        AnimationUtil.getDownTranslateY(view);
=======
                                            //                                            AnimationUtil.downTranslateY(view);
>>>>>>> dev

                                            super.onDismissed(transientBottomBar, event);
                                        }
                                    })
                                    .setAction(action, listener);
        snackbar.getView()
                .setBackgroundColor(color);
        return snackbar;
    }

    /**
     * 保存图片提示
     */
    public static Snackbar showSnakbarLongs(final Context context,
                                            final View view,
                                            String message,
                                            String action,
                                            final int mPosition,
                                            final PagerGirlAdapter mPagerGirlAdapter)
    {
        int color = Color.argb(255, 239, 152, 59);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                                    .setAction(action, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            SaveImageUtil.savePic(context,
                                                                  mPosition,
                                                                  mPagerGirlAdapter);
                                        }
                                    });
        snackbar.getView()
                .setBackgroundColor(color);
        return snackbar;
    }

    /**
     * 网络异常提示
     */
    public static Snackbar showSnakbarShort(View view, String message)
    {
        int      color    = Color.rgb(255, 64, 129);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.getView()
                .setBackgroundColor(color);

        return snackbar;
    }

    /**
     * 关闭Snakbar
     */
    public static Snackbar dismissSnakbar(View view)
    {

        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);

        snackbar.dismiss();
        return snackbar;
    }


}
