package zhiwenyan.cmccaifu.com.android2017.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.view.customview.LetterSideBarView;

public class IndexActivity extends AppCompatActivity implements LetterSideBarView.SideBarTouchListener {
    private TextView mTv;
    LetterSideBarView mLetterSideBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mTv = (TextView) findViewById(R.id.tv);
        mLetterSideBarView = (LetterSideBarView) findViewById(R.id.lv);
        mLetterSideBarView.setOnSideBarTouchListener(this);
    }

    @Override
    public void onTouch(String letter, boolean isTouch) {
        mTv.setText(letter);
        if (isTouch) {
            mTv.setVisibility(View.VISIBLE);
        } else {
            mTv.setVisibility(View.GONE);
        }
    }
}
