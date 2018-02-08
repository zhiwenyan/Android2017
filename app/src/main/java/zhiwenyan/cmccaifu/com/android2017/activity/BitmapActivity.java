package zhiwenyan.cmccaifu.com.android2017.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import zhiwenyan.cmccaifu.com.android2017.R;

public class BitmapActivity extends AppCompatActivity {
    private DisplayMetrics mDisplayMetrics;
    private int mScreenWidth;
    private int mScreenHeight;
    private ImageView mImageView;
    private Bitmap mBitmap;
    private int shiftPx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        mImageView = findViewById(R.id.img);
        mDisplayMetrics = new DisplayMetrics();
        mDisplayMetrics = this.getResources().getDisplayMetrics();
        mScreenWidth = mDisplayMetrics.widthPixels;
        mScreenHeight = mDisplayMetrics.heightPixels;
    }

    private void getPic() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 0x01);
    }

    File file;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 0x01 && resultCode == RESULT_OK) {
                String path = getRealPathFromUrl(data.getData());
                file = new File(path);
                if (file == null) {
                    return;
                }
                if (file.length() == 0) {
                    file.delete();
                    return;
                }
                Log.i("TAG", "fileName: " + file.getName() + "filePath=" + file.getPath());
                FileInputStream fileInputStream = new FileInputStream(file);
                mBitmap = BitmapFactory.decodeStream(fileInputStream);
                mImageView.setImageBitmap(mBitmap);
                Log.i("TAG", "fileByteCount: " + mBitmap.getByteCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void changePicOptions() {
        if (file == null) {
            return;
        }
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(file), null, options);
            int width = options.outWidth;
            int height = options.outHeight;
            int scale = 2;
            while (true) {
                if (width / scale < mScreenWidth) {
                    break;
                }
                scale *= 2;
            }
            scale /= 2;
            BitmapFactory.Options options1 = new BitmapFactory.Options();
            options1.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file), null, options1);
            Log.i("TAG", "fileByteCount: " + bitmap.getByteCount());
            mImageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeRGB() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Log.i("TAG", "changeRGB: ");
            FileInputStream fileInputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream, null, options);
            mImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    FileInputStream fin;

    private void ptarLoad() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BitmapFactory.Options tempOptions = new BitmapFactory.Options();
            tempOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(fileInputStream, null, tempOptions);
            int width = tempOptions.outWidth;
            int height = tempOptions.outHeight;
            //设置图片显示的中心区域
            fin = new FileInputStream(file);
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(fin, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            Rect rect = new Rect(width / 2 - mScreenWidth / 2 + shiftPx, height / 2 - mScreenHeight / 2,
                    width / 2 + mScreenWidth / 2 + shiftPx, height / 2 + mScreenHeight / 2);
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(rect, options);
            mImageView.setImageBitmap(bitmap);
            Log.i("TAG", "ptarLoad: " + bitmap.getByteCount());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 根据uri获取文件的path
     *
     * @param contentUri
     * @return
     */
    private String getRealPathFromUrl(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.getContentResolver().query(contentUri, proj, null, null, null);
        while (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(columnIndex);
        }
        cursor.close();
        return res;
    }
}
