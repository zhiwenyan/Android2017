package zhiwenyan.cmccaifu.com.android2017.view.redPacket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.R;

public class RedPacketViewActivity extends AppCompatActivity {
    private RedPacketView mRedPacketView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_packet_view);
        mRedPacketView = findViewById(R.id.redPacketView);
        mRedPacketView.startAnimation(1, 2);
    }

}
