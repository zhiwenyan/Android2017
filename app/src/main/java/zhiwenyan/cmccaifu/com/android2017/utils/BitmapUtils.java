package zhiwenyan.cmccaifu.com.android2017.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Description:
 * Data：4/19/2018-1:54 PM
 *
 * @author yanzhiwen
 */
public class BitmapUtils {

    /**
     * @param resources 资源
     * @param resId     图片id
     * @param reqWidth  要求的宽
     * @param reqHeight 要求的高
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int resId, int reqWidth, int reqHeight) {
        //Options 只保存图片尺寸大小，不保存图片到内存
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int width = options.outWidth;
        int height = options.outHeight;
        int inSampleSize = 1;
        int widthRatio = Math.round((float) width / (float) reqWidth);
        int heightRatio = Math.round((float) height / (float) reqHeight);
        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        return inSampleSize;
    }
}

