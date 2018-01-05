package zhiwenyan.cmccaifu.com.android2017.view.touchView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

public class ViewTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_touch);
        findViewById(R.id.tv).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("TAG", "OnTouchListener--->onTouch: " + event.getAction());
                return false;
            }
        });
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "onClick");
            }
        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.i("TAG", "OnTouchListener-->onTouchEvent: " + event.getAction());
//        return super.onTouchEvent(event);
//    }
//

}
