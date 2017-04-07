package photoview.yibao.com.photoview.util;

import android.support.design.widget.Snackbar;
import android.view.View;


/**
 * 作者：Stran on 2017/3/28 01:31
 * 描述：${}
 * 邮箱：strangermy@outlook.com
 */
public class SnakbarUtil {
    /**
     * 修改Snakbar背景颜色
     * @param view
     * @param message
     * @param color
     * @return
     */
    public static Snackbar showSuccessStatus(View view, String message, int color) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.getView()
                .setBackgroundColor(color);

        return snackbar;
    }

    /**
     * showLongSankBar
     * @param view
     * @param message
     * @param action
     * @param listener
     * @return
     */
    public static Snackbar showSnakbarLong(final View view,
                                           String message,
                                           String action,
                                           View.OnClickListener listener)
    {

        return Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                       .addCallback(new Snackbar.Callback() {
                                        @Override
                                        public void onShown(Snackbar sb) {
                                            super.onShown(sb);
                                        }

                                        @Override
                                        public void onDismissed(Snackbar transientBottomBar,
                                                                int event)
                                        {
                                                                                        AnimationUtil.getDownTranslateY(view);

                                            super.onDismissed(transientBottomBar, event);
                                        }
                                    })
                       .setAction(action, listener);
    }

    /**
     * showShortSnakbar
     * @param view
     * @param message
    //     * @param action
    //     * @param listener
     *
     */
    public static Snackbar showSnakbarShort(View view, String message, int color)
    {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.getView()
                .setBackgroundColor(color);

        return snackbar;
    }

    /**
     * dismissSnakbarShort
     * @param view
     * @return
     */
    public static Snackbar dismissSnakbar(View view)
    {
        Snackbar snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT);
        snackbar.dismiss();
        return snackbar;
    }


}
