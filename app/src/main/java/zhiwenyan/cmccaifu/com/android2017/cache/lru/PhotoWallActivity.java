package zhiwenyan.cmccaifu.com.android2017.cache.lru;

import android.os.Environment;
import android.util.Log;
import android.widget.GridView;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class PhotoWallActivity extends BaseActivity {

    private GridView mPhotoWall;

    private PhotoWallAdapter adapter;


    @Override
    protected void init() {
        doSetToolBarTitle("Lru缓存图片");
        mPhotoWall = ( GridView ) findViewById(R.id.photo_wall);
        adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWall);
        mPhotoWall.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_wall;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("---", "onCreate: " + this.getCacheDir().getPath());
        Log.i("---", "onCreate: " + this.getExternalCacheDir().getPath());
        Log.i("---", "onCreate: " + this.getExternalFilesDir("cache").getPath()); //一般放一些长时间保存的数据
        Log.i("---", "onCreate: " + Environment.getExternalStorageDirectory().getPath()); //般存放临时缓存数据
        Log.i("---", "onCreate: " + Environment.getExternalStoragePublicDirectory(Environment.MEDIA_BAD_REMOVAL));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出程序时结束所有的下载任务
        adapter.cancelAllTasks();
    }
}
