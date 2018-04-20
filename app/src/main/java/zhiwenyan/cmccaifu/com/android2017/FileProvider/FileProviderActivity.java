package zhiwenyan.cmccaifu.com.android2017.FileProvider;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import zhiwenyan.cmccaifu.com.android2017.R;

public class FileProviderActivity extends AppCompatActivity {
    private String mCurrentPhotoPath;
    private static final int REQUEST_CODE_TAKE_PHOTO = 0x110;
    private ImageView mIvPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fil_provider);
        mIvPhoto = findViewById(R.id.img);
    }


    /**
     * 拍照案例
     * 大家应该对于手机拍照一定都不陌生，在希望得到一张高清拍照图的时候，我们通过Intent会传递一个File的Uri给相机应用。
     *
     * @param view
     */
    public void takePhotoNoCompress(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                    .format(new Date()) + ".png";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            mCurrentPhotoPath = file.getAbsolutePath();
            Uri fileUri = FileProviderUtil.getUriForFile(this, file);
            //EXTRA_OUTPUT，转为了setClipData，并直接给我们添加了WRITE和READ权限。
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_PHOTO) {
            mIvPhoto.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        }
    }

    private void installApp() {
        File apkFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "cashmall.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //适配下android7.0
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, this.getPackageName() + ".fileprovider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    private void installApp1() {
        File apkFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "cashmall.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        FileProviderUtil.setIntentDataAndType(this, intent,
                "application/vnd.android.package-archive", apkFile, true);
        startActivity(intent);
    }
}

