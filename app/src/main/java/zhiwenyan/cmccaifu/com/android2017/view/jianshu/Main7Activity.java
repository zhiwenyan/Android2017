package zhiwenyan.cmccaifu.com.android2017.view.jianshu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import zhiwenyan.cmccaifu.com.android2017.R;

public class Main7Activity extends AppCompatActivity {
    private JianshuCircleView mJianshuCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        mJianshuCircleView = findViewById(R.id.jianshuCircleView);
//        for (int i = 0; i < 5; i++) {
//            MultiShapeView multiShapeView = new MultiShapeView(this);
//            multiShapeView.setImageResource(R.mipmap.click_head_img_0);
//            multiShapeView.setShape(MultiShapeView.SHAPE_CIRCLE);
//            mJianshuCircleView.addCircleView(multiShapeView);
//        }


        //圆形图
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.mipmap.photo_two);
        RoundedBitmapDrawable circleDrawable = RoundedBitmapDrawableFactory.create(getResources(),
                BitmapFactory.decodeResource(getResources(), R.mipmap.photo_two));
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()));
        ImageView image3 = findViewById(R.id.img);
        image3.setImageDrawable(circleDrawable);
    }
}
