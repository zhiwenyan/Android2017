package zhiwenyan.cmccaifu.com.android2017.ViewGroup.Scroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import zhiwenyan.cmccaifu.com.android2017.R;

public class ScrollerActivity extends AppCompatActivity {
    private LinearLayout mLayout;
    private Button mScrollToBtn;
    private Button mScrollByBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoller);
        mLayout = (LinearLayout) findViewById(R.id.layout);
        mScrollToBtn = (Button) findViewById(R.id.btn1);
        mScrollByBtn = (Button) findViewById(R.id.btn2);

        mScrollToBtn.setOnClickListener(v -> {
            mLayout.scrollTo(-60, -100);  //初始位置移动
        });
        mScrollByBtn.setOnClickListener(v -> {
            mLayout.scrollBy(-60, -100);  //相对于当前位置移动

        });
    }
}
