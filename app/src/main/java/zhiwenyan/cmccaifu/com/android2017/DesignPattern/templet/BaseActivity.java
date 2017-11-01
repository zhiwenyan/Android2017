package zhiwenyan.cmccaifu.com.android2017.DesignPattern.templet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yanzhiwen on 2017/11/1.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initTitle();
        initView();
        initData();

    }

    public abstract int getLayoutId();

    public abstract int initTitle();

    public abstract int initView();

    public abstract int initData();


}
