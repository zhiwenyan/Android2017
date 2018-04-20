package zhiwenyan.cmccaifu.com.android2017.rxjava;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zhiwenyan.cmccaifu.com.android2017.R;


public class RxJavaActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bitmap bitmap = (Bitmap) msg.obj;
            mImageView.setImageBitmap(bitmap);
        }
    };

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {

                        } else {

                        }
                    }
                });
        mImageView = (ImageView) findViewById(R.id.img);
        //给图片加一张水印 一般的写法
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg");
//                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    Bitmap bmp = createWaterMark(bitmap, "RxJava");
//                    Message message = mHandler.obtainMessage();
//                    message.obj = bmp;
//                    mHandler.sendMessage(message);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        Observable.just("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg")
                .map(new Function<String, Bitmap>() { // 事件变换
                    @Override
                    public Bitmap apply(@NonNull String urlPath) throws Exception {
                        URL url = new URL(urlPath);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    }
                })
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
                        bitmap = createWaterMark(bitmap, "RxJava2.0");
                        return bitmap;
                    }
                })
                .subscribeOn(Schedulers.io())  //子线程
              //  .observeOn(AndroidSchedulers.mainThread()) //主线程
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        mImageView.setImageBitmap(bitmap);
                    }
                });
        // 怎么办到的，这个框架怎么写？可能会涉及到哪些内容
        // Observable
    }

    private Bitmap createWaterMark(Bitmap bitmap, String mark) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setTextSize(150f);
        paint.setColor(Color.parseColor("#c5576370"));
        paint.setAntiAlias(true);
        paint.setDither(true);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawText(mark, 0, height / 2, paint);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bmp;

    }
}
