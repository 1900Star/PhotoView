package photoview.yibao.com.photoview.util;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import photoview.yibao.com.photoview.bean.GirlBean;
import photoview.yibao.com.photoview.bean.ResultsBean;

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
    public static List<ResultsBean> mResults;


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


    /**
     * 保存图片
     */
    public List<ResultsBean> initGirlData()
    {

        LogUtil.d("进入下载方法", "////////////////////////////////////////////");
        Request request = new Request.Builder().url(url)
                                               .build();
        mClient.newCall(request)
               .enqueue(new Callback() {
                   @Override
                   public void onFailure(Call call, IOException e) {
                       //下载失败
                       //                       listener.onDownloadFailed();
                   }

                   @Override
                   public void onResponse(Call call, Response response)
                           throws IOException
                   {

                       //                       InputStream      is   = null;
                       byte[]           buff = new byte[1024 * 4];
                       int              len  = 0;
                       FileOutputStream fos  = null;

                       //保存地址
                       //                       String savePath = isExistDir(saveDir);
                       //                       byte[] bytes = response.body()
                       //                                              .bytes();
                       String json = response.body()
                                             .string();

                       //                       LogUtil.d(
                       //                               "================Girl 哈哈 =="+json);

                       Gson     gson     = new Gson();
                       GirlBean girlData = gson.fromJson(json, GirlBean.class);
                       mResults = girlData.getResults();

                       ResultsBean resultsBean = mResults.get(460);

                       String ganhuo_id = resultsBean.getUrl();
                       LogUtil.d("__-----++=++++++++++这是图片的 长度=====Url ==" + mResults.size());
                       LogUtil.d("__-----++=++++++++++这是图片的 =====Url ==" + ganhuo_id);


                       //                       File file = new File(saveDir, getNameFromUrl(url));
                       long length = response.body()
                                             .contentLength();
                       //                       fos = new FileOutputStream(saveDir);
                       long sum = 0;
                       //                       while ((len = is.read(buff)) != -1) {
                       //                           fos.write(buff, 0, len);
                       //                           sum += len;
                       //                           int progress = (int) (sum * 1.0f / length * 100);
                       //                           listener.onDownloading(progress);

                       //                       }
                       //                       fos.flush();
                       //                       listener.onDownloadSuccess();

                   }
               });
        return mResults;

    }


}







