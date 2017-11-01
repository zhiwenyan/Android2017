package zhiwenyan.cmccaifu.com.android2017.view.shapeview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import butterknife.BindView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ShapeViewActivity extends BaseActivity {

    @BindView(R.id.shapeView1)
    MultiShapeView mShapeView1;
    @BindView(R.id.shapeView2)
    MultiShapeView mShapeView2;
    @BindView(R.id.shapeView3)
    MultiShapeView mShapeView3;

    @BindView(R.id.img)
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
