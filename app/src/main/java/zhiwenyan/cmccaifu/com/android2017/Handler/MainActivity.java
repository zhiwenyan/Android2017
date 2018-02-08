package zhiwenyan.cmccaifu.com.android2017.Handler;

import android.os.Handler;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

public class MainActivity extends Activity {
    private TextView mTextView;

    private Handler1 mHandler1 = new Handler1() {
        @Override
        public void handlerMessage(Message1 msg) {
            super.handlerMessage(msg);
            mTextView.setText((String) msg.obj);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate()方法执行了");
        mTextView = findViewById(R.id.text_view);

        new Handler().post(new Runnable() {
            @Override
            public void run() {

            }
        });

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message1 message1 = new Message1();
            message1.obj = "后台数据";
            mHandler1.sendMessage(message1);
            //mTextView.setText("后台数据");
        }).start();

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume()方法执行了");

    }
}
