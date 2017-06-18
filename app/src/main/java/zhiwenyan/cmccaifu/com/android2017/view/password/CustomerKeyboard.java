package zhiwenyan.cmccaifu.com.android2017.view.password;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Created by zhiwenyan on 6/2/17.
 */

public class CustomerKeyboard extends LinearLayout implements View.OnClickListener {
    private CustomerKeyboardClickListener mCustomerKeyboardClickListener;

    public CustomerKeyboard(Context context) {
        this(context, null);
    }

    public CustomerKeyboard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerKeyboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.costomer_keyboard, this);
        setItemClickListener(this);
    }


    private void setItemClickListener(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                //不断的给里面所有的View设置setOnClickListener
                View childView = ((ViewGroup) view).getChildAt(i);
                setItemClickListener(childView);
            }
        } else {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            String number = ((TextView) v).getText().toString().trim();
            if (mCustomerKeyboardClickListener != null) {
                mCustomerKeyboardClickListener.click(number);
            }
        }
        if (v instanceof ImageView) {
            if (mCustomerKeyboardClickListener != null) {
                mCustomerKeyboardClickListener.delete();
            }
        }
    }

    //设置点击回调监听
    public void setCustomerKeyboardClickListener(CustomerKeyboardClickListener customerKeyboardClickListener) {
        this.mCustomerKeyboardClickListener = customerKeyboardClickListener;
    }

    public interface CustomerKeyboardClickListener {
        void click(String number);

        void delete();
    }
}
