package zhiwenyan.cmccaifu.com.android2017.DesignPattern.observeable.simple5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;

public class InsuranceActivity extends AppCompatActivity {

    @BindView(R.id.nameEdt)
    EditText nameEdt;
    @BindView(R.id.ageEdt)
    EditText ageEdt;
    @BindView(R.id.submitBtn)
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submitBtn)
    public void onClick() {
    }
}
