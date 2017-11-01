package zhiwenyan.cmccaifu.com.android2017.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class BehaviorActivity extends BaseActivity {
    @BindView(R.id.btn)
    Button mBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_behavior;
    }

    @Override
    protected void init() {
        mBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        v.setX(event.getRawX() - v.getWidth() / 2);
                        v.setY(event.getRawY() - v.getHeight() / 2);
                        break;
                }
                return false;
            }
        });

    }

}
