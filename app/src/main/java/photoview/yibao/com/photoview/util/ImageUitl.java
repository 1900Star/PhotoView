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

/**
 * 作者：Stran on 2017/3/23 03:23
 * 描述：${TODO}
 * 邮箱：strangermy@outlook.com
 */
public class ImageUitl {
    private static ImageUitl    downloadUtil;
    private final  OkHttpClient mClient;


    private ImageUitl() {
        mClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
    }


    static String TAG = "ImageUitl";
    public static String[] picUrlArr = {"https://images.unsplash.com/uploads/141327328038701afeede/eda0fb7c?ixlib=rb-0.3.5&q=100&fm=jpg&crop=entropy&cs=tinysrgb&s=b1418e0650f85155b76164dc6655c8a0",
                                        "http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-23-17265820_645330569008169_4543676027339014144_n.jpg",
                                        "http://pic8.nipic.com/20100623/2568996_083300720588_2.jpg",
                                        "http://pic8.nipic.com/20100623/2568996_083301157944_2.jpg",
                                        "http://www.asahi.com/showbiz/gallery/20110309sada/images/home03.jpg",
                                        "http://s9.sinaimg.cn/middle/64df0f90gc499680089f8&690",
                                        "http://c.hiphotos.baidu.com/zhidao/pic/item/fc1f4134970a304eef4479edd1c8a786c9175c47.jpg",
                                        "http://imgsrc.baidu.com/baike/pic/item/0e2442a7d933c8950df8a76ad51373f082020018.jpg",
                                        "http://img.yule.com.cn/upload/jpg/nagasawamasami/yule0092.jpg",
                                        "http://posters.imdb.cn/ren-pp/0619178/YB8kFxWnY_1190911372.jpg",
                                        "http://imgsrc.baidu.com/baike/pic/item/e4dde71190ef76c604622df99816fdfaae5167b4.jpg",
                                        "http://cdn.duitang.com/uploads/item/201603/27/20160327100800_ysidF.thumb.700_0.jpeg",
                                        "http://image.xinmin.cn/2012/06/27/20120627113211233486.jpg",
                                        "http://cdn.duitang.com/uploads/item/201205/28/20120528121844_CNemU.jpeg",
                                        "http://img4.duitang.com/uploads/item/201412/18/20141218103953_QVWRs.jpeg",
                                        "http://img5.duitang.com/uploads/item/201408/09/20140809211026_4EXAN.jpeg",
                                        "http://news.xinhuanet.com/shuhua/2012-12/01/124032455_161n.jpg",
                                        "http://h5.86.cc/walls/20160303/1440x900_3ea4f573d7355e5.jpg",
                                        "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg",
                                        "http://img3.duitang.com/uploads/item/201302/15/20130215110229_HnMGj.jpeg",
                                        "http://imgsrc.baidu.com/baike/pic/item/a08b87d6277f9e2f79c9bcd81a30e924b999f366.jpg",
                                        "http://pic9.nipic.com/20100819/2568996_152536117301_2.jpg",
                                        "http://pic9.nipic.com/20100819/2568996_152535489648_2.jpg",
                                        "http://img0.imgtn.bdimg.com/it/u=290611155,1857671296&fm=214&gp=0.jpg",
                                        "http://i2.w.yun.hjfile.cn/slide/201506/201506052390726352.jpg",
                                        "http://i.ce.cn/ce/xwzx/shgj/gdxw/201605/03/W020160503497361794077.jpg",
                                        "http://img3.duitang.com/uploads/item/201605/12/20160512192447_mFjeG.jpeg",
                                        "http://img.7160.com/uploads/allimg/160707/1-160FG34214.jpg",
                                        "http://cdn.duitang.com/uploads/item/201509/17/20150917160112_QZCKu.jpeg",
                                        "http://image11.m1905.com/uploadfile/2009/1221/20091221061006829.jpg",
                                        "http://qximg.lightplan.cc/2016/05/10/1462863397324242.gif?imageView2/2/w/900/h/1600",
                                        "http://img0.imgtn.bdimg.com/it/u=1036761068,3361653394&fm=214&gp=0.jpg",
                                        "http://imgsrc.baidu.com/baike/pic/item/7c1ed21b0ef41bd576ca27bf51da81cb38db3de4.jpg",
                                        "http://img5.cache.netease.com/photo/0003/2015-01-04/AF45G9UF00B60003.jpg",
                                        "http://www.yoka.com/dna/pics/Star/ba15111c/164/d35e311de7dc7ec97d.jpg",
                                        "http://img1.ph.126.net/s3vPdXW29wx-tiJAeFky3g==/4851502698685350157.jpg",
                                        "http://m15.mask9.com/sites/default/files/styles/lg/public/graphics/20150605/164324-ee2d7e6528487b1b554952e006ac34c14ba93fef-23/people-singer-shao-yibei-p2-mask9.jpg?itok=MBWKmFkb",
                                        "http://img4.imgtn.bdimg.com/it/u=4199968251,2321509994&fm=214&gp=0.jpg",
                                        "http://www.wed114.cn/jiehun/uploads/allimg/130422/23_130422144522_4.jpg",
                                        "http://img2.imgtn.bdimg.com/it/u=3773886563,3596131833&fm=214&gp=0.jpg",
                                        "http://ent.sun0769.com/music/news/201311/W020131126420260416278.jpg",
                                        "http://cdn.duitang.com/uploads/item/201407/25/20140725121058_j2wKR.jpeg",
                                        "http://a3.att.hudong.com/60/01/01200000193375136359017074846.jpg",
                                        "http://img4.duitang.com/uploads/item/201602/21/20160221131416_nyZAa.thumb.700_0.png",
                                        "http://nobon.me/wp-content/uploads/2016/10/iphone_KIMINONAHA__0003_Unknown-3.jpg",
                                        "http://wx4.sinaimg.cn/mw690/005T4vvxly1falxqf43j9j31sc15m4qp0.jpg",
                                        "http://wx3.sinaimg.cn/mw690/005T4vvxly1falxqfrogzj31sc16oke40.jpg",
                                        "http://wx4.sinaimg.cn/mw690/005T4vvxly1falxqdyn4aj31sc16oe840.jpg",
                                        "http://imgsrc.baidu.com/baike/pic/item/d1160924ab18972b2c2fd087e5cd7b899e510a62.jpg",
                                        "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg",
                                        "http://qz_coffee.dupao.com/XiuXiuUpload/SharePic/XiuXiu2013820850243.jpg",
                                        "http://imgsrc.baidu.com/forum/pic/item/b25f0e465153f0868b139989.jpg",
                                        "http://album.sina.com.cn/pic/49cef469020003w6",
                                        "http://ww1.sinaimg.cn/large/610dc034gw1fb6aqccs3nj20u00u0wk4.jpg",
                                        "http://img2.imgtn.bdimg.com/it/u=3271180130,1167809918&fm=23&gp=0.jpg"};

    public static ImageUitl get() {
        if (downloadUtil == null) {
            downloadUtil = new ImageUitl();
        }
        return downloadUtil;
    }

    public static ImageView getGlidLoadPic(Context context, int position, ImageView imageView) {


        String url = picUrlArr[position];
        System.out.println("this is ImageUrl    " + url);

        Glide.with(context)
             .load(url)
             .asBitmap()
             .placeholder(R.drawable.girl)
             .error(R.drawable.gg)
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







