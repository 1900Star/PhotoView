package photoview.yibao.com.photoview.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import photoview.yibao.com.photoview.MyApplication;
import photoview.yibao.com.photoview.R;
import photoview.yibao.com.photoview.activity.MainActivity;
import photoview.yibao.com.photoview.adapter.MyPagerAdapter;
import photoview.yibao.com.photoview.bean.GirlBean;
import photoview.yibao.com.photoview.bean.ResultsBean;
import photoview.yibao.com.photoview.http.Api;
import photoview.yibao.com.photoview.view.ProgressView;

import static photoview.yibao.com.photoview.util.GirlUitl.mResults;


/**
 * 作者：Stran on 2017/3/23 19:52
 * 描述：保存图片到本地
 * 邮箱：strangermy@outlook.com
 */
public class SavePic {

    private static String  TAG              = "SavePic";
    static         String  url              = Constans.BASE_URL + "/1000/1";
    /**
     * 图片下载的实时进度
     */
    private static int     DOWN_PROGRESS    = 1;
    /**
     * 图片下载成功
     */
    private static int     DOWN_PIC_SUCCESS = 0;
    private static boolean isSuccess        = false;

    static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            //将下载进度设置到ProgressBar上
            if (DOWN_PROGRESS == what) {
                int          progress     = (int) msg.obj;
                ProgressView progressView = MainActivity.getProgressView();
                progressView.setIcon(R.drawable.share_evernote);
                progressView.setBackground(new ColorDrawable(Color.TRANSPARENT));
                progressView.setMax(100);
                progressView.setProgress(progress);
            } else if (DOWN_PIC_SUCCESS == what) {
                //弹出下载完成通知
                MainActivity.showSavePicSuccess();

            }


        }
    };
    private Bitmap bitmap;


    public static void savePic(final Context mContext,
                               int itemPosition,
                               final MyPagerAdapter mAdapter,
                               final ProgressView progressView)
    {

        String url  = Api.picUrlArr[itemPosition];
        String name = url.substring(url.lastIndexOf("/") + 1);
        LogUtil.d("当前的Url是      " + url);
        File file = FileUtil.getFile(name);


        ImageUitl.get()
                 .downloadPic(url, file.toString(),

                              new ImageUitl.OnDownloadListener() {
                                  @Override
                                  public void onDownloadSuccess() {
                                      LogUtil.d(TAG, "下载成功  ");
                                      File file1 = new File(Environment.getExternalStorageDirectory(),
                                                            Constans.PIC_NAME + ".jpg");
                                      isSuccess = true;

                                      try {
                                          FileOutputStream fos = new FileOutputStream(file1);
                                          //通过mAdapter拿到当显示的图片
                                          ImageView iv     = (ImageView) mAdapter.getPrimaryItem();
                                          Bitmap    bitmap = BitmapUtil.drawableToBitmap(iv.getDrawable());
                                          //将图片保存到SD卡上
                                          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                                          LogUtil.d(TAG, "下载成功  文件大小==" + bitmap.getByteCount());
                                          //用Handler通知图片保存成功
                                          Message message = new Message();
                                          message.obj = bitmap;
                                          message.what = DOWN_PIC_SUCCESS;
                                          mHandler.sendMessage(message);
                                          //发个意图让MediasSanner去扫描SD卡，将下载的图片更新到图库
                                          Intent intent = new Intent();
                                          intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                          intent.setData(Uri.fromFile(file1));
                                          mContext.sendBroadcast(intent);
                                          LogUtil.d(TAG, "广播发出去了");


                                      } catch (FileNotFoundException e) {
                                          e.printStackTrace();
                                      }

                                  }

                                  @Override
                                  public void onDownloading(int progress) {
                                      Message message = new Message();
                                      message.obj = progress;
                                      message.what = DOWN_PROGRESS;
                                      mHandler.sendMessage(message);


                                  }

                                  @Override
                                  public void onDownloadFailed() {


                                  }
                              });
    }

    /**
     * 加载图片数据
     */
    public static List<ResultsBean> initGirlData()
    {

        LogUtil.d("进入下载方法", "////////////////////////////////////////////");
        Request request = new Request.Builder().url(url)
                                               .build();
        MyApplication.defaultOkHttpClient()
                     .newCall(request)
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


                         }
                     });

        return mResults;
    }


}
