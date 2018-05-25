package zhiwenyan.cmccaifu.com.android2017.activity;

import android.os.Bundle;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

public class HandleThreadActivity extends AppCompatActivity {
    private HandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlr_thread);
        mHandlerThread = new HandlerThread("HandleThreadActivity");
        mHandlerThread.start();
    }

}
