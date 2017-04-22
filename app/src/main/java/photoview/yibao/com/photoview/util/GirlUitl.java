package photoview.yibao.com.photoview.util;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import photoview.yibao.com.photoview.bean.GirlBean;
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.bean.ResultsBean;
import photoview.yibao.com.photoview.http.GirlServiceRx;

/**
 * 作者：Stran on 2017/3/23 03:23
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class GirlUitl {
    static String TAG = "GirlUitl";
    private static GirlUitl     downloadUtil;
    private        OkHttpClient mClient;
    String url = Constans.BASE_URL + "/1000/1";
    private List<ResultsBean> mResults;
    private static final List<String> list = new ArrayList<>();

    GirlUitl() {
        if (mClient != null) {}
        mClient = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS)
                                            .writeTimeout(3, TimeUnit.SECONDS)
                                            .readTimeout(3, TimeUnit.SECONDS)
                                            .build();
    }

    public static GirlUitl get() {
        if (downloadUtil == null) {
            downloadUtil = new GirlUitl();
        }
        return downloadUtil;
    }


    public static void getRx() {

        RxjavaUtils.getRxjava()
                   .create(GirlServiceRx.class)
                   .getGril("福利", 100, 1)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<GirlBean>() {
                       @Override
                       public void onSubscribe(Disposable d) {
                           LogUtil.d("======       Disposable  ===    ");
                       }

                       @Override
                       public void onNext(GirlBean girlBean) {
                           List<ResultsBean> results = girlBean.getResults();
                           for (int i = 0; i < results.size(); i++) {

                               list.add(results.get(i)
                                               .getUrl());

                           }
                           EventBus.getDefault()
                                   .post(new GirlData(list));


                       }

                       @Override
                       public void onError(Throwable e) {

                       }

                       @Override
                       public void onComplete() {
                           LogUtil.d("======       complete  ===    ");

                       }
                   });


    }

}







