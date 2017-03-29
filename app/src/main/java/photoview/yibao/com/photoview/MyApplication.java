package photoview.yibao.com.photoview;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作者：Stran on 2017/3/23 15:12
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class MyApplication
        extends Application
{
    private static MyApplication mApplication;
    public static String currentGirl = "http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-23-17265820_645330569008169_4543676027339014144_n.jpg";

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mApplication = this;


    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS)
                                                        .writeTimeout(3, TimeUnit.SECONDS)
                                                        .readTimeout(3, TimeUnit.SECONDS)
                                                        .build();
        return client;
    }

    public static MyApplication getIntstance() {
        return mApplication;
    }
}
