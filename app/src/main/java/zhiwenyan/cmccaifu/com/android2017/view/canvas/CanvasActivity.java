package zhiwenyan.cmccaifu.com.android2017.view.canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

import zhiwenyan.cmccaifu.com.android2017.R;

public class CanvasActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
    }
}
