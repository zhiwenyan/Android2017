package zhiwenyan.cmccaifu.com.android2017.view.shapeview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import butterknife.InjectView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ShapeViewActivity extends BaseActivity {

    @InjectView(R.id.shapeView1)
    MultiShapeView mShapeView1;
    @InjectView(R.id.shapeView2)
    MultiShapeView mShapeView2;
    @InjectView(R.id.shapeView3)
    MultiShapeView mShapeView3;

    @InjectView(R.id.img)
    ImageView mImg;

    @Override
    protected void init() {
        super.init();
        mShapeView1.setImageResource(R.mipmap.photo_one);
        mShapeView2.setImageResource(R.mipmap.photo_two);
        mShapeView3.setImageResource(R.mipmap.photo_three);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.photo_one);
        CircleImageView roundImageView = new CircleImageView(bitmap);
        mImg.setImageDrawable(roundImageView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shape_view;
    }
}
