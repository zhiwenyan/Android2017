package zhiwenyan.cmccaifu.com.android2017.DesignPattern.aop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;

public class AopActivity extends AppCompatActivity {

    @BindView(R.id.checkNet)
    Button mCheckNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.checkNet)
    @CheckNet
    public void onViewClicked() {
        Log.i("TAG", "onViewClicked: ");
    }
}
