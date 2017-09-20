package zhiwenyan.cmccaifu.com.android2017.ViewGroup.touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import zhiwenyan.cmccaifu.com.android2017.R;

import static zhiwenyan.cmccaifu.com.android2017.R.id.tv;

public class ViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group2);


        findViewById(tv).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("TAG", "tv-->OnTouchListener: " + event.getAction());
                return false;
            }
        });

//        findViewById(tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("TAG", "tv-->onClick: ");
//            }
//        });
    }

}
