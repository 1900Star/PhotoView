package photoview.yibao.com.photoview.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.bean.GirlBean;
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
             .placeholder(R.drawable.girl)
             .error(R.drawable.beautiful)
             .into(imageView);

        return imageView;
    }

    /**
     * 保存图片
     */
    public static void downloadPic(final String url,
                                   final String saveDir,
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
                             LogUtil.i(
                                     "================Call call, Response response==================================    已经下载 完了");

                             InputStream      is   = null;
                             byte[]           buff = new byte[1024 * 4];
                             int              len  = 0;
                             FileOutputStream fos  = null;

                             //保存地址
                             //                       String savePath = isExistDir(saveDir);
                             is = response.body()
                                          .byteStream();
                             File file = new File(saveDir, getNameFromUrl(url));
                             long length = response.body()
                                                   .contentLength();
                             fos = new FileOutputStream(saveDir);
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

    //*********************************************************************
    private static OnData mOnData;

    public interface OnData {
        void getGrilsData(List<String> list);
    }

    public void setOnData(OnData data) {
        this.mOnData = data;
    }

    /**
     * 加载干货妹子图片数据
     */
    public static List<String> getGirls() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://gank.io/")
                                                  .addConverterFactory(GsonConverterFactory.create())
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
                        //                        ResultsBean bean = results.get(1);
                        for (int i = 0; i < results.size(); i++) {

                            //                            LogUtil.d("哈哈       ===" + results.get(i)
                            //                                                              .getUrl());
                            list.add(results.get(i)
                                            .getUrl());

                        }
                        if (mOnData != null) {
                            mOnData.getGrilsData(list);
                        }

                    }

                    @Override
                    public void onFailure(retrofit2.Call<GirlBean> call, Throwable t) {

                    }

                });

                        LogUtil.d("     88888===============       " + list.size());
        return list;
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
    private String isExistDir(String saveDir)
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







