package photoview.yibao.com.photoview.util;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import photoview.yibao.com.photoview.R;

import static photoview.yibao.com.photoview.http.Api.picUrlArr;

/**
 * 作者：Stran on 2017/3/23 03:23
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class ImageUitl {
    private static ImageUitl    downloadUtil;
    private final  OkHttpClient mClient;


    private ImageUitl() {
        mClient = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS)
                                            .writeTimeout(3, TimeUnit.SECONDS)
                                            .readTimeout(3, TimeUnit.SECONDS)
                                            .build();
    }


    static        String   TAG       = "ImageUitl";


    public static ImageUitl get() {
        if (downloadUtil == null) {
            downloadUtil = new ImageUitl();
        }
        return downloadUtil;
    }

    public static ImageView glideLoadPic(Context context, int position, ImageView imageView) {


        String url = picUrlArr[position];
        //        String url = MainActivity.initGirlData().get(position)
        //                                 .getUrl();
        System.out.println("this is ImageUrl    " + url);

        Glide.with(context)
             .load(url)
             .asBitmap()
             .placeholder(R.drawable.girl)
             .error(R.drawable.beautiful)
             .into(imageView);

        return imageView;
    }

    /**
     * 保存图片
     */
    public void downloadPic(final String url,
                            final String saveDir,
                            final OnDownloadListener listener)
    {

        LogUtil.d("进入下载方法", "////////////////////////////////////////////");
        Request request = new Request.Builder().url(url)
                                               .build();
        mClient.newCall(request)
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

    /**
     * @param url
     * @return
     * 从下载连接中解析出文件名
     */
    @NonNull
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }

    //下载图片回调接口
    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress
         * 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }

}







