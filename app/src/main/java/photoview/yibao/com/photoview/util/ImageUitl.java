package photoview.yibao.com.photoview.util;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import photoview.yibao.com.photoview.MyApplication;
import photoview.yibao.com.photoview.bean.DownProgress;
import photoview.yibao.com.photoview.http.Api;

/**
 * 作者：Stran on 2017/3/23 03:23
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class ImageUitl {

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

                             LogUtil.d("HHHHHHHHHHHH=== ===     " + getNameFromUrl(url));
                             File file = new File(FileUtil.IMAGE_PATH, getNameFromUrl(url));
                             long length = response.body()
                                                   .contentLength();
                             fos = new FileOutputStream(file);
                             long sum = 0;
                             while ((len = is.read(buff)) != -1) {
                                 fos.write(buff, 0, len);
                                 sum += len;
                                 int progress = (int) (sum * 1.0f / length * 100);
//                                 listener.onDownloading(progress);
                                 EventBus.getDefault()
                                         .post(new DownProgress(progress));

                             }
                             fos.flush();
                             listener.onDownloadSuccess();

                         }
                     });


    }


    //下载图片回调接口
    public interface OnDownloadListener {

        void onDownloadSuccess();


//        void onDownloading(int progress);


        void onDownloadFailed();
    }


    //初始化数据
    public static List<String> getDefultUrl(List<String> list) {
        Collections.addAll(list, Api.picUrlArr);
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

}







