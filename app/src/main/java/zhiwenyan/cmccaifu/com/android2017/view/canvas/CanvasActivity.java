package zhiwenyan.cmccaifu.com.android2017.view.canvas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.util.Arrays;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.utils.DrawableUtils;

public class CanvasActivity extends AppCompatActivity {
    private static final String TAG = CanvasActivity.class.getSimpleName();
    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        mIv = findViewById(R.id.iv);
        CheckView checkView = findViewById(R.id.checkView);
        checkView.check();


        byte[] bytes = DrawableUtils.bitmapToByte(BitmapFactory.
                decodeResource(getResources(), R.mipmap.animation_img2));

        Log.i(TAG, "bytes=" + Arrays.toString(bytes));
        String encodeToString = Base64.encodeToString(bytes, Base64.DEFAULT);

        Log.i(TAG, "encodeToString=" + encodeToString);

        Bitmap bitmap = DrawableUtils.bytesToBitmap(bytes);
        mIv.setImageBitmap(bitmap);
    }
}
