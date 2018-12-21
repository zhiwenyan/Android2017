package zhiwenyan.cmccaifu.com.android2017.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Description:可以简单地理解为 Bitmap 储存的是 像素信息，Drawable 储存的是 对 Canvas 的一系列操作。
 * <p>
 * 而 BitmapDrawable 储存的是「把 Bitmap 渲染到 Canvas 上」这个操作。
 * Data：12/12/2018-10:59 AM
 *
 * @author yanzhiwen
 */
public class DrawableUtils {
    //drawable--->bitmap

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return (( BitmapDrawable ) drawable).getBitmap();
        } else {
            // 取 drawable 的宽高
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();
            Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ?
                    Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;

            Bitmap bitmap = Bitmap.createBitmap(w, h, config);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, w, h);
            // 把 drawable 内容画到画布中
            drawable.draw(canvas);
            return bitmap;
        }
    }

    //bitmap->drawable
    public static Drawable bitmapToDrawable(Resources resources, Bitmap bitmap) {
        return new BitmapDrawable(resources, bitmap);
    }

    //Bitmap → byte[]
    public static byte[] bitmapToByte(Bitmap bmp) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, b);
        return b.toByteArray();
    }

    public static Bitmap bytesToBitmap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }
}
