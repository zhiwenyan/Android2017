package zhiwenyan.cmccaifu.com.android2017.banner.banner;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class BannerActivity extends BaseActivity {
    public static final String URL1 = "http://pb9.pstatp.com/origin/24990000d4c26180d691";
    public static final String URL2 = "http://pb9.pstatp.com/origin/1dcf002c646ac321e698";
    private BannerView mBannerView;
    private String url = "http://is.snssdk.com/2/essay/discovery/v3/?&device_platform=android&device_type=Redmi+Note+3&iid=6152551759&" +
            "manifest_version_code=570&longitude=113.000366&latitude=28.171377&update_version_code=5701&aid=7&channel=360";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_banner;
    }

    @Override
    protected void init() {
        doSetToolBarTitle("自定义Banner实现无限轮播");
//        String info = HttpUtils.getInstance(url).execute();
        mBannerView = findViewById(R.id.bannerView);
        mBannerView.setAdapter(new BannerAdapter() {
            @Override
            public View getView(int position, View convertView) {
                ImageView bannerIv = null;
                if (convertView == null) {
                    bannerIv = new ImageView(BannerActivity.this);
                    bannerIv.setScaleType(ImageView.ScaleType.FIT_XY);
                } else {
                    bannerIv = (ImageView) convertView;
                    Log.i("TAG", "getView: 界面复用" + bannerIv);
                }
                if (position == 0) {
                    Glide.with(BannerActivity.this).load(URL1).into(bannerIv);
                } else {
                    Glide.with(BannerActivity.this).load(URL2).into(bannerIv);
                }
                return bannerIv;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
        mBannerView.startLoop();
        mBannerView.setScrollerDuration(1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
