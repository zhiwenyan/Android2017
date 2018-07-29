package zhiwenyan.cmccaifu.com.android2017.Socket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

public class SoketActivity extends AppCompatActivity {
    private EchoServer1 mEchoServer;
    private EchoClient1 mEchoClient;

    private TextView mMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soket);
        final int port = 9877;
        mEchoServer = new EchoServer1(port);
        mEchoServer.run();
        mEchoClient = new EchoClient1("localhost", port);

        mMsg = findViewById(R.id.msg);
        findViewById(R.id.send).setOnClickListener((view) -> {
            String msg = mMsg.getText().toString();
            if (TextUtils.isEmpty(msg)) {
                return;
            }
            mEchoClient.send(msg);
            mMsg.setText("");
        });
    }
}
