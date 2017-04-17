package photoview.yibao.com.photoview.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import photoview.yibao.com.photoview.MyApplication;
import photoview.yibao.com.photoview.bean.GirlBean;
import photoview.yibao.com.photoview.bean.GirlData;
import photoview.yibao.com.photoview.bean.ResultsBean;
import photoview.yibao.com.photoview.http.Api;
import photoview.yibao.com.photoview.http.GirlService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Stran on 2017/3/23 03:23
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class ImageUitl {
    static List<ResultsBean> mResults;
    private static       String       url  = Constans.BASE_URL + "/1000/1";
    private static final List<String> list = new ArrayList<>();

    //Glide加载图片
    public static ImageView glideLoadPic(Context context, String url, ImageView imageView) {


        Glide.with(context)
             .load(url)
             .asBitmap()
             .diskCacheStrategy(DiskCacheStrategy.ALL)
             .into(imageView);

        return imageView;
    }

    /**
     * 保存图片
     */
    public static void downloadPic(final String url,

                                   final OnDownloadListener listener)
    {

        Request request = new Request.Builder().url(url)
                                               .build();
        MyApplication.defaultOkHttpClient()
                     .newCall(request)
                     .enqueue(new Callback() {
                         @Override
                         public void onFailure(Call call, IOException e) {
                             //下载失败
                             listener.onDownloadFailed();
                         }

                         @Override
                         public void onResponse(Call call, Response response)
                                 throws IOException
                         {

                             InputStream      is   = null;
                             byte[]           buff = new byte[1024 * 4];
                             int              len  = 0;
                             FileOutputStream fos  = null;

                             //保存地址
//                                                    String savePath = isExistDir(saveDir);
                             is = response.body()
                                          .byteStream();

                             LogUtil.d("HHHHHHHHHHHH=== ===     "+getNameFromUrl(url));
                             File file = new File(FileUtil.IMAGE_PATH, getNameFromUrl(url));
                             long length = response.body()
                                                   .contentLength();
                             fos = new FileOutputStream(file);
                             long sum = 0;
                             while ((len = is.read(buff)) != -1) {
                                 fos.write(buff, 0, len);
                                 sum += len;
                                 int progress = (int) (sum * 1.0f / length * 100);
                                 listener.onDownloading(progress);

                             }
                             fos.flush();
                             listener.onDownloadSuccess();

                         }
                     });


    }


    //下载图片回调接口
    public interface OnDownloadListener {

        void onDownloadSuccess();


        void onDownloading(int progress);


        void onDownloadFailed();
    }


    /**
     * 加载干货妹子图片数据
     */
    public static void getGirls() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io/")
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .client(MyApplication.defaultOkHttpClient())
                                                  .build();

        retrofit.create(GirlService.class)
                .getGrils("福利", 10000, 1)
                .enqueue(new retrofit2.Callback<GirlBean>() {

                    @Override
                    public void onResponse(retrofit2.Call<GirlBean> call,
                                           retrofit2.Response<GirlBean> response)
                    {
                        List<ResultsBean> results = response.body()
                                                            .getResults();
                        for (int i = 0; i < results.size(); i++) {

                            list.add(results.get(i)
                                            .getUrl());

                        }
                        EventBus.getDefault()
                                .post(new GirlData(list));

                    }

                    @Override
                    public void onFailure(retrofit2.Call<GirlBean> call, Throwable t) {

                    }

                });

    }

    //初始化数据
    public static List<String> getUrl(List<String> list) {
        for (int i = 0; i < Api.picUrlArr.length; i++) {
            list.add(Api.picUrlArr[i]);
        }
        return list;
    }

    /**
     * @param url
     * @return
     * 从下载连接中解析出文件名
     */
    @NonNull
    private static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException
     * 判断下载目录是否存在
     */
    private static String isExistDir(String saveDir)
            throws IOException
    {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory() + saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }
}







