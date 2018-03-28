package zhiwenyan.cmccaifu.com.android2017.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import zhiwenyan.cmccaifu.com.android2017.R;

public class PermissionActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
    }

    public void phoneClick(View view) {
//        //检查有没有权限
//        int isPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
//        if (isPermission == PackageManager.PERMISSION_GRANTED) {
//            callPhone();
//        } else {
//            //申请权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE);
//        }

        PermissionHelper.with(this)
                .requestCode(PERMISSION_REQUEST_CODE)
                .requestPermission(Manifest.permission.CALL_PHONE)
                .request();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults != null && grantResults.length > 0) {
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    callPhone();
//                } else {
//                    Toast.makeText(PermissionActivity.this, "您拒绝了拨打电话", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
        PermissionHelper.requestPermissionsResult(this, requestCode, permissions);
    }

    @PermissionSuccess(requestCode = PERMISSION_REQUEST_CODE)
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:888888");
        intent.setData(uri);
        startActivity(intent);
    }

    @PermissionFailure(requestCode = PERMISSION_REQUEST_CODE)
    public void failPhone() {
        Toast.makeText(this, "您拒绝了打电话", Toast.LENGTH_SHORT).show();
    }
}
