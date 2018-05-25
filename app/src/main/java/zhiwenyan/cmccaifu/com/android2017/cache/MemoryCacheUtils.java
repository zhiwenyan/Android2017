package zhiwenyan.cmccaifu.com.android2017.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by zhiwenyan on 4/19/17.
 */

public class MemoryCacheUtils {
    private LruCache<String, Bitmap> mMemoryCache;

    public MemoryCacheUtils() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 8);//得到手机最大允许内存的1/8,即超过指定内存,则开始回收
        mMemoryCache = new LruCache<String, Bitmap>(maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                super.sizeOf(key,value);
                return value.getByteCount();
            }
        };
    }

    /**
     * 从内存中读图片
     *
     * @param url
     */
    public Bitmap getBitmapFromMemory(String url) {
        //Bitmap bitmap = mMemoryCache.get(url);//1.强引用方法
            /*2.弱引用方法
            SoftReference<Bitmap> bitmapSoftReference = mMemoryCache.get(url);
            if (bitmapSoftReference != null) {
                Bitmap bitmap = bitmapSoftReference.get();
                return bitmap;
            }
            */
        return mMemoryCache.get(url);

    }

    /**
     * 往内存中写图片
     *
     * @param url
     * @param bitmap
     */
    public void setBitmapToMemory(String url, Bitmap bitmap) {
        //mMemoryCache.put(url, bitmap);//1.强引用方法
            /*2.弱引用方法
            mMemoryCache.put(url, new SoftReference<>(bitmap));
            */
        mMemoryCache.put(url, bitmap);
    }
}
