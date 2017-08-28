package zhiwenyan.cmccaifu.com.android2017.view.BezierCurve;

import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhiwenyan.cmccaifu.com.android2017.R;

public class BezierCurveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_curve);
        MessageBubbleView.attach(findViewById(R.id.tv), new MessageBubbleView.MessageBubbleListener() {
            @Override
            public void restore() {

            }

            @Override
            public void dismiss(PointF pointF) {

            }
        });
    }
}
