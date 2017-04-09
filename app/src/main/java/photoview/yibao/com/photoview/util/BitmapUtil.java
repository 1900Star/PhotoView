package photoview.yibao.com.photoview.util;

import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;


/**
 * 作者：Stran on 2017/3/23 15:26
 * 描述：${ bitmap处理工具类}
 * 邮箱：strangermy@outlook.com
 */
public class BitmapUtil {

    public static android.graphics.Bitmap drawableToBitmap(Drawable drawable) {

        android.graphics.Bitmap bitmap = android.graphics.Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE
                ? android.graphics.Bitmap.Config.ARGB_8888

                : android.graphics.Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);


        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;
    }


}
