package zhiwenyan.cmccaifu.com.android2017.view.switchButton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.function.Supplier;

import zhiwenyan.cmccaifu.com.android2017.R;

public class SwitchButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_button);
        String str = "";

    }


    public void test(Supplier<String> block) {
        System.out.println("=======" + block.get());
    }
}