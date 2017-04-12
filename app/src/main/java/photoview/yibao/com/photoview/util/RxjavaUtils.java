package photoview.yibao.com.photoview.util;

import io.reactivex.Observable;
import photoview.yibao.com.photoview.MyApplication;
import photoview.yibao.com.photoview.http.GirlService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/10 17:22
 */
public class RxjavaUtils {

    /*    int drawableRes = ...;
        ImageView imageView = ...;
    Observable.create(new OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes));
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Observer<Drawable>() {
            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
        */
    public static void getRxjava() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io/")
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .client(MyApplication.defaultOkHttpClient())
                                                  .build();
        GirlService service = retrofit.create(GirlService.class);

        Observable serviceGril = service.getGril("福利", 100, 1);
    }

}
