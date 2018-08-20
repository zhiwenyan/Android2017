package zhiwenyan.cmccaifu.com.android2017.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

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
        // 设置该属性为true，不加载图片到内存，只返回图片的宽高到options中。
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        options.inJustDecodeBounds = true;
        //先加载图片
        BitmapFactory.decodeResource(resources, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 重新设置该属性为false，加载图片返回
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

    private static LruCache<String, Bitmap> mMemoryCache;

    private static void memoryCache() {
        // 获取到可用内存的最大值，使用内存超出这个值会引起OutOfMemory异常。
        // LruCache通过构造函数传入缓存值，以KB为单位。
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 使用最大可用内存值的1/8作为缓存的大小。
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }
}

