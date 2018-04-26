package zhiwenyan.cmccaifu.com.android2017.view.password;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import zhiwenyan.cmccaifu.com.android2017.R;

public class PasswordActivity extends AppCompatActivity implements CustomerKeyboard.CustomerKeyboardClickListener, PasswordEditText.PasswordFullListener, View.OnClickListener {
    private CustomerKeyboard mCustomerKeyboard;
    private PasswordEditText mPasswordEditText;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        mButton = (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(this);


    }

    @Override
    public void click(String number) {
        mPasswordEditText.addPassword(number);
    }

    @Override
    public void delete() {
        mPasswordEditText.deletePassword();
    }

    @Override
    public void passwordFull(String password) {
        //去后台填充密码
        Toast.makeText(this, "你输入的密码是：" + password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        View keyBoardView = LayoutInflater.from(this).inflate(R.layout.dialog_keyboard, null);
        mCustomerKeyboard = (CustomerKeyboard) keyBoardView.findViewById(R.id.keyboard);
        mPasswordEditText = (PasswordEditText) keyBoardView.findViewById(R.id.pw);
        mCustomerKeyboard.setCustomerKeyboardClickListener(this);
        mPasswordEditText.setPasswordFullListener(this);
        mPasswordEditText.setEnabled(false);
        PopupWindow popupWindow = new PopupWindow(keyBoardView, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.anim.slid_in_bottom);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(mButton, Gravity.BOTTOM, 0, 0);
    }
}
