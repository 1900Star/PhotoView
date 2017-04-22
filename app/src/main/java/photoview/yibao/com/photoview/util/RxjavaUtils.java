package photoview.yibao.com.photoview.util;


import photoview.yibao.com.photoview.MyApplication;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/10 17:22
 */
public class RxjavaUtils {

    private static Retrofit retrofit;

    public static Retrofit getRxjava() {
        if (retrofit == null) {
            synchronized (RxjavaUtils.class) {
                retrofit = new Retrofit.Builder().baseUrl(Constans.GANK_API)
                                                 .addConverterFactory(GsonConverterFactory.create())
                                                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                                 .client(MyApplication.defaultOkHttpClient())
                                                 .build();

            }

        }
        return retrofit;


    }

}
