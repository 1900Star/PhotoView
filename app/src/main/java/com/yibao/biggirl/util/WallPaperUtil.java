package com.yibao.biggirl.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.yibao.biggirl.girl.GirlAdapter;

import java.io.IOException;


/**
 * 作者：Stran on 2017/3/28 22:22
 * 描述：${壁纸设置}
 * 邮箱：strangermy@outlook.com
 */
public class WallPaperUtil {

    /**
     * 将当前图片设置为壁纸
     * @param adapter
     */

    public static void setWallPaper(Context context, GirlAdapter adapter) {
        WallpaperManager mWpManager = WallpaperManager.getInstance(context);
        ImageView        view       = (ImageView) adapter.getPrimaryItem();
        Bitmap           bitmap     = BitmapUtil.drawableToBitmap(view.getDrawable());

        try {
            mWpManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 从图库选择壁纸
     * @param context
     */
    public static void choiceWallPaper(Context context) {
        Intent chooseIntent = new Intent(Intent.ACTION_SET_WALLPAPER);
        context.startActivity(Intent.createChooser(chooseIntent, "选择壁纸"));
    }


//    static class RxBus {
//
//
//        private final Subject<Object, Object>
//                        rxBus = new SerializedSubject<>(PublishSubject.create());
//
//        private RxBus() {
//
//        }
//
//        public static RxBus getInstance() {
//            return RxbusHolder.instance;
//        }
//
//        public static class RxbusHolder {
//            private static final RxBus instance = new RxBus();
//        }
//
//        public void send(Bundle o) {
//            rxBus.onNext(o);
//        }
//
//        public Observable<Object> toObserverable() {
//            return rxBus;
//        }
//    }

}
