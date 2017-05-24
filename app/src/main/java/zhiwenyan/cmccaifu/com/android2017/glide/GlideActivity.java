package zhiwenyan.cmccaifu.com.android2017.glide;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.InjectView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class GlideActivity extends BaseActivity {

    public static final String URL = "http://img1.xcarimg.com/b101/s5797/m_20140114092801483977.jpg";
    @InjectView(R.id.loadImg)
    ImageView mLoadImg;

    @InjectView(R.id.loadImg1)
    ImageView mRoundLoadImg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide;
    }


    @OnClick({R.id.loadLocalImgBtn, R.id.loadNetImgBtn, R.id.transform, R.id.round})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loadLocalImgBtn:
                Glide.with(this)
                        .load(R.drawable.ic_menu_gallery)
                        .into(mLoadImg);
                break;
            case R.id.loadNetImgBtn:
                Glide.with(this)
                        .load(URL)  //加载图片的地址
                        .crossFade() //默认加载的动画
                        .override(600, 600) //指定图片的大小
                        .placeholder(R.drawable.ic_android_black_24dp) //占位图
                        .into(mLoadImg);

                break;
            case R.id.transform:
                Glide.with(this)
                        .load(URL)
                        .transform(new CircleBitmapTransformation(this))
                        .into(mRoundLoadImg);
                break;
            case R.id.round:
                Glide.with(this)
                        .load(URL)
                        .transform(new RoundBitmapTransformation(this))
                        .into(mRoundLoadImg);
                break;

        }
    }
}
