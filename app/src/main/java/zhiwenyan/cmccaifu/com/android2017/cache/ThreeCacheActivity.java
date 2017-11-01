package zhiwenyan.cmccaifu.com.android2017.cache;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ThreeCacheActivity extends BaseActivity {

    @BindView(R.id.get)
    Button mGet;
    @BindView(R.id.infoTv)
    TextView mInfoTv;
    @BindView(R.id.img)
    ImageView mImg;

    MyBitmapUtils myBitmapUtils;
    private StringBuilder mSb=new StringBuilder();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_three_cache;
    }

    @OnClick(R.id.get)
    public void onClick() {
        myBitmapUtils = new MyBitmapUtils();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0x1);
        } else {
            myBitmapUtils.disPlay(mImg, "http://img1.xcarimg.com/b101/s5797/m_20140114092801483977.jpg");
        }
    }

    class MyBitmapUtils {
        private NetCacheUtils mNetCacheUtils;
        private LocalCacheUtils mLocalCacheUtils;
        private MemoryCacheUtils mMemoryCacheUtils;

        public MyBitmapUtils() {
            mMemoryCacheUtils = new MemoryCacheUtils();
            mLocalCacheUtils = new LocalCacheUtils();
            mNetCacheUtils = new NetCacheUtils(mLocalCacheUtils, mMemoryCacheUtils);
        }

        public void disPlay(ImageView ivPic, String url) {
            Bitmap bitmap;
            //内存缓存
            bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
            if (bitmap != null) {
                ivPic.setImageBitmap(bitmap);
                mSb.append("从内存获取图片....." + "\n");
                mInfoTv.setText(mSb);
                return;

            }
            //本地缓存
            bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
            if (bitmap != null) {
                ivPic.setImageBitmap(bitmap);
                mSb.append("从本地获取图片....." + "\n");
                mInfoTv.setText(mSb);
                //从本地获取图片后,保存至内存中
                mMemoryCacheUtils.setBitmapToMemory(url, bitmap);
                return;
            }
            //网络缓存
            mNetCacheUtils.getBitmapFromNet(ivPic, url);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            myBitmapUtils.disPlay(mImg, "http://img1.xcarimg.com/b101/s5797/m_20140114092801483977.jpg");
        }
    }
}
