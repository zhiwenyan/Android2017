package zhiwenyan.cmccaifu.com.android2017.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhiwenyan on 4/19/17.
 */

public class NetCacheUtils {
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public NetCacheUtils(LocalCacheUtils localCacheUtils, MemoryCacheUtils memoryCacheUtils) {
        this.mLocalCacheUtils = localCacheUtils;
        this.mMemoryCacheUtils = memoryCacheUtils;

    }

    public void getBitmapFromNet(ImageView imageView, String url) {
        new BitmapTask().execute(imageView, url);

    }

    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {
        private ImageView img;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... params) {
            img = (ImageView) params[0];
            url = (String) params[1];
            return downLoadBitmap(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                img.setImageBitmap(bitmap);
                mMemoryCacheUtils.setBitmapToMemory(url, bitmap);
                mLocalCacheUtils.setBitmapToLocal(url, bitmap);
            }
        }
    }

    /**
     * 网络下载图片
     *
     * @param url
     * @return
     */
    private Bitmap downLoadBitmap(String url) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                //图片压缩
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;//宽高压缩为原来的1/2
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream(), null, options);
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return null;
    }
}
