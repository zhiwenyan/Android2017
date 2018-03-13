package zhiwenyan.cmccaifu.com.android2017.view.gesture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import zhiwenyan.cmccaifu.com.android2017.R;

public class LockView1Activity extends AppCompatActivity {
    private GesturePasswordView mGesturePasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_view1);
        mGesturePasswordView = findViewById(R.id.gesturePasswordView);
        mGesturePasswordView.setGesturePasswordViewListener(new GesturePasswordViewListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LockView1Activity.this, "解锁成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError() {
                Toast.makeText(LockView1Activity.this, "至少四个点", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(LockView1Activity.this, "解锁失败", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
