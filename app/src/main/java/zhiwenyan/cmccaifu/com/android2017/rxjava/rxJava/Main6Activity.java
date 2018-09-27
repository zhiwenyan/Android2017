package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.functions.Consumer;
import zhiwenyan.cmccaifu.com.android2017.R;

public class Main6Activity extends AppCompatActivity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        mImageView = findViewById(R.id.img);
//        new Thread(() -> System.out.println("--")).start();
//        io.reactivex.Observable.just("")
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        Observable.just("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg")
//                .subscribe(item -> System.out.println("------" + item));
        Observable.just("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1536577188315&di=168ec3" +
                "83c58a013e746c87ca21ccfbbb&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Ff" +
                "af2b2119313b07e5119db2301d7912397dd8c71.jpg")
                .map(s -> {   //事件变换 ObservableMap
                    //第五步
                    System.out.println("s==" + s);
                    System.out.println("threadName1=" + Thread.currentThread().getName());

                    InputStream inputStream = null;
                    try {
                        URL url = new URL(s);
                        HttpURLConnection urlConnection = ( HttpURLConnection ) url.openConnection();
                        inputStream = urlConnection.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                })
                .map(bitmap -> {
                    System.out.println("bitmap==" + bitmap);
                    bitmap = createWaterMark(bitmap, "RxJava2.0");
                    System.out.println("threadName2=" + Thread.currentThread().getName());

                    return bitmap;
                })
           //     .subscribeOn(Schedulers.io())  //子线程
            //    .observeOn(AndroidSchedulers.mainThread()) //主线程
                .subscribe(new zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava.Consumer<Bitmap>() {
                    @Override
                    public void onNext(Bitmap item) {
                        mImageView.setImageBitmap(item);
                    }
                });
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            //申请权限
                        } else {
                            //拒绝
                        }
                    }
                });
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
