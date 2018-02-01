package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import zhiwenyan.cmccaifu.com.android2017.R;

public class Main6Activity extends AppCompatActivity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        mImageView = (ImageView) findViewById(R.id.img);
        new Thread(() -> System.out.println("--")).start();
        Observable.just("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg").subscribe(new Consumer<String>() {
            @Override
            public void onNext(String item) {
                System.out.println("------" + item);
            }
        });
        new Thread(() -> {
            Observable.just("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg")
                    .map(s -> {
                        //第五步
                        System.out.println("s==" + s);
                        InputStream inputStream = null;
                        try {
                            URL url = new URL(s);
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
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
                        return bitmap;
                    })
                    .subscribe(bmp -> {
                        //第七步 显示图片
                        runOnUiThread(() -> mImageView.setImageBitmap(bmp));
                    });
        }).start();

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
