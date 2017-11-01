package zhiwenyan.cmccaifu.com.android2017.view.matrix;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class MatrixActivity extends BaseActivity {

    @BindView(R.id.scaleBtn)
    Button mScaleBtn;
    @BindView(R.id.traslateBtn)
    Button mTraslateBtn;
    @BindView(R.id.rorationBtn)
    Button mRorationBtn;
    @BindView(R.id.skewBtn)
    Button mSkewBtn;
    @BindView(R.id.img)
    MatrixView mImg;

    @Override
    protected void init() {
        super.init();
        doSetToolBarTitle("Matrix");
        mImg.setImageResource(R.mipmap.photo_one);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_matrix;
    }

    @OnClick({R.id.scaleBtn, R.id.traslateBtn, R.id.rorationBtn, R.id.skewBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scaleBtn:
                mImg.setScaleBitmap(0.5f, 0.5f);
                break;
            case R.id.traslateBtn:
                mImg.setTranslateBitmap();
                break;
            case R.id.rorationBtn:
                mImg.setRorationBitmap();
                break;
            case R.id.skewBtn:
                mImg.setSkewBitmap();
                break;
        }
    }
}
