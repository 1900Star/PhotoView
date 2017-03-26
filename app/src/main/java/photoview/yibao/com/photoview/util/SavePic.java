package photoview.yibao.com.photoview.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import photoview.yibao.com.photoview.activity.MainActivity;
import photoview.yibao.com.photoview.adapter.MyPagerAdapter;


/**
 * 作者：Stran on 2017/3/23 19:52
 * 描述：保存图片到本地
 * 邮箱：strangermy@outlook.com
 */
public class SavePic {

    private static String  TAG       = "SavePic";
    private static boolean isSuccess = false;

    private Context mContext;
    static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    //弹出保存成功提示
                    MainActivity.showSavePicSuccess();
                    break;
                default:
                    break;
            }


        }
    };


    public static void savePic(final Context mContext,
                               int itemPosition,
                               final MyPagerAdapter mAdapter)
    {

        String url  = ImageUitl.picUrlArr[itemPosition];
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
                                          message.what = 2;
                                          mHandler.sendMessage(message);


                                          //发个意图让Mediascanner去扫描SD卡，将下载的图片更新到图库
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

                                      LogUtil.d("实时下载进度     ", progress + "");


                                  }

                                  @Override
                                  public void onDownloadFailed() {


                                  }
                              });
    }


}
