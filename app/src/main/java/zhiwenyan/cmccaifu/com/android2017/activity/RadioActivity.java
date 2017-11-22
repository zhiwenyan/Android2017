package zhiwenyan.cmccaifu.com.android2017.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import zhiwenyan.cmccaifu.com.android2017.R;

public class RadioActivity extends AppCompatActivity {
    private RadioGroup mRadioGroup;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg);
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRadioGroup.getVisibility() == View.GONE) {
                    mRadioGroup.removeAllViews();
                    mRadioGroup.setVisibility(View.VISIBLE);
                    for (int i = 0; i < 3; i++) {
                        final CheckBox checkBox = new CheckBox(RadioActivity.this);
                        mRadioGroup.addView(checkBox);
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    for (int i = 0; i < 3; i++) {

                                    }
                                } else {
                                    checkBox.setChecked(false);
                                }
                            }
                        });
                    }
                } else {
                    mRadioGroup.setVisibility(View.GONE);

                }
            }
        });


    }
}
