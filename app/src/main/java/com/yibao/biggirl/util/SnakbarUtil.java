package com.yibao.biggirl.util;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.yibao.biggirl.adapter.PagerGirlAdapter;

import static android.support.design.widget.Snackbar.make;


/**
 * 作者：Stran on 2017/3/28 01:31
 * 描述：${}
 * 邮箱：strangermy@outlook.com
 */
public class SnakbarUtil {
    /**
     * 下载成功提示
     */
    public static void showSuccessStatus(View view) {
        int      color    = Color.rgb(90, 181, 63);
        Snackbar snackbar = make(view, "图片保存成功 -_-", Snackbar.LENGTH_LONG);
        snackbar.getView()
                .setBackgroundColor(color);
        snackbar.show();

    }


    /**
     * 保存图片提示
     */
    public static void savePic(final Context context,
                               final View view,
                               final String url,
                               final PagerGirlAdapter mPagerGirlAdapter)
    {


        int color = Color.argb(255, 245, 115, 160);

        Snackbar snackbar = make(view, "可以将图片保存起来-_-", Snackbar.LENGTH_LONG)
                                    .setAction("保存图片", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            SaveImageUtil.savePic(context, url, mPagerGirlAdapter);
                                        }
                                    });
        snackbar.getView()
                .setBackgroundColor(color);
        snackbar.show();
    }

    /**
     * 网络异常提示
     */
    public static void netErrors(View view)
    {
        int      color    = Color.rgb(255, 64, 129);
        Snackbar snackbar = make(view, "网络异常，请检查您的网络连接 -_-", Snackbar.LENGTH_SHORT);
        snackbar.getView()
                .setBackgroundColor(color);

        snackbar.show();
    }

    /**
     * 退出程序
     */
    public static void finishActivity(View view)
    {
        int      color    = Color.argb(255, 230, 195, 65);
        Snackbar snackbar = make(view, "再按一次我就离开了 -_-", Snackbar.LENGTH_SHORT);
        snackbar.getView()
                .setBackgroundColor(color);
        snackbar.show();

    }

    /**
     * 关闭Snakbar
     */
    public static void setWallpaer(View view)
    {
        int      color    = Color.rgb(90, 181, 63);
        Snackbar snackbar = make(view, "壁纸设置成功  -_-", Snackbar.LENGTH_LONG);
        snackbar.getView()
                .setBackgroundColor(color);
        snackbar.show();

    }


}
