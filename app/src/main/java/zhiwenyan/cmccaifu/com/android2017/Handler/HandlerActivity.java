package zhiwenyan.cmccaifu.com.android2017.Handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

public class HandlerActivity extends AppCompatActivity {
    private TextView mTextView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //主线程  线程之间的通信
            mTextView.setText("textView");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mTextView = (TextView) findViewById(R.id.tv);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Looper.prepare();
                Handler handler = new Handler();
                Message message = Message.obtain();
                Looper.loop();
                message.obj = "Handler Test";
                mHandler.sendMessage(message);  //放入消息队列
            }
        }).start();
    }

}
