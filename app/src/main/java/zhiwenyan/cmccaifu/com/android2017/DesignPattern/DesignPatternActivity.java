package zhiwenyan.cmccaifu.com.android2017.DesignPattern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.bottomTab.BottomTabItemActivity;
import zhiwenyan.cmccaifu.com.android2017.R;

public class DesignPatternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_pattern);
        findViewById(R.id.btn1).setOnClickListener(v -> startActivity
                (new Intent(DesignPatternActivity.this, BottomTabItemActivity.class)));
    }
}
