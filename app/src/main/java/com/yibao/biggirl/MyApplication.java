package com.yibao.biggirl;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.WebView;
import com.yibao.biggirl.util.ConfigUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：Stran on 2017/3/23 15:12
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class MyApplication
        extends Application
{
    private static MyApplication appContext;
    public static  String currentGirl = "com.yibao.biggirl.http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-23-17265820_645330569008169_4543676027339014144_n.jpg";
    private static String THEME_KEY   = "theme_mode";
    private boolean isNight;

    public static MyApplication getIntstance() {
        if (appContext == null) {
            appContext = new MyApplication();
        }
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        //Tencent x5内核
        WebView webView = new WebView(this);
        int width = webView.getView()
                           .getWidth();
        LeakCanary.install(this);
        Fresco.initialize(this);
        appContext = this;

    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request();
            //                LogUtil.d("request ====     "+request.toString());
            Response proceed = chain.proceed(request);
            //                LogUtil.d("proced=====      "+proceed);
            return proceed;
        })
                                                        .connectTimeout(3, TimeUnit.SECONDS)
                                                        .writeTimeout(3, TimeUnit.SECONDS)
                                                        .readTimeout(3, TimeUnit.SECONDS)
                                                        .build();
        return client;
    }

    private void initThemeMode() {
        isNight = ConfigUtil.getBoolean(THEME_KEY, false);
        if (isNight) {
            //夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            //白天模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void setTheme(AppCompatActivity activity, boolean mode) {
        if (isNight == mode) {
            return;
        }
        if (!mode) {
            //白天模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            activity.getDelegate()
                    .setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            //白天模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            activity.getDelegate()
                    .setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        isNight = mode;
        ConfigUtil.putBoolean(THEME_KEY, isNight);
        activity.recreate();
    }

    /**
     * 刷新UI_MODE模式
     */
    public void refreshResources(Activity activity) {
        isNight = ConfigUtil.getBoolean(THEME_KEY, false);
        if (isNight) {
            updateConfig(activity, Configuration.UI_MODE_NIGHT_YES);
        } else {
            updateConfig(activity, Configuration.UI_MODE_NIGHT_NO);
        }
    }


    /**
     * google官方bug，暂时解决方案
     * 手机切屏后重新设置UI_MODE模式（因为在dayNight主题下，切换横屏后UI_MODE会出错，会导致资源获取出错，需要重新设置回来）
     */
    private void updateConfig(Activity activity, int uiNightMode) {
        Configuration newConfig = new Configuration(activity.getResources()
                                                            .getConfiguration());
        newConfig.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        newConfig.uiMode |= uiNightMode;
        activity.getResources()
                .updateConfiguration(newConfig, null);
    }


}
