package com.yibao.biggirl.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import com.yibao.biggirl.girl.GirlAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * 作者：Stran on 2017/3/23 19:52
 * 描述：保存图片到本地
 * 邮箱：strangermy@outlook.com
 */
public class SaveImageUtil {





    public static void savePic(final Context mContext, String url, final GirlAdapter mAdapter)
    {

        String name = url.substring(url.lastIndexOf("/") + 1);
        LogUtil.d("name ====    " + name);
        //        File   file = FileUtil.getFile(name);

        ImageUitl.downloadPic(url,

                              new ImageUitl.OnDownloadListener() {
                                  @Override
                                  public void onDownloadSuccess() {
                                      File file1 = new File(Environment.getExternalStorageDirectory(),
                                                            Constans.PIC_NAME + ".jpg");

                                      try {
                                          FileOutputStream fos = new FileOutputStream(file1);
                                          //通过mAdapter拿到当显示的图片
                                          ImageView iv     = (ImageView) mAdapter.getPrimaryItem();
                                          Bitmap    bitmap = BitmapUtil.drawableToBitmap(iv.getDrawable());
                                          //将图片保存到SD卡上
                                          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                                          //                                          LogUtil.d(TAG, "下载成功  文件大小==" + bitmap.getByteCount());

                                          //发个意图让MediasSanner去扫描SD卡，将下载的图片更新到图库
                                          Intent intent = new Intent();
                                          intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                          intent.setData(Uri.fromFile(file1));
                                          mContext.sendBroadcast(intent);
                                          //                                          LogUtil.d(TAG, "广播发出去了");


                                      } catch (FileNotFoundException e) {
                                          e.printStackTrace();
                                      }

                                  }

//                                  @Override public void onDownloading(int progress) {}

                                  @Override
                                  public void onDownloadFailed() {
                                      LogUtil.d("--------------------------    failed");

                                  }
                              });
    }


}
