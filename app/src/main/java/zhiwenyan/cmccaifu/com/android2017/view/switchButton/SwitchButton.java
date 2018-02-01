package zhiwenyan.cmccaifu.com.android2017.view.switchButton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import zhiwenyan.cmccaifu.com.android2017.R;

/**
 * Description:
 * Data：1/30/2018-2:30 PM
 *
 * @author: yanzhiwen
 */
public class SwitchButton extends AppCompatButton {
    private Drawable mDrawable;
    private int mTextColor;

    public SwitchButton(Context context) {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.buttonStyle);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SwitchButton, defStyleAttr, 0);
        boolean selected = array.getBoolean(R.styleable.SwitchButton_selected, false);
        setSelected(selected);
    }


    public void setSelected(boolean isSelected) {
        if (isSelected) {
            mDrawable = ContextCompat.getDrawable(getContext(), R.drawable.common_button_dark_bg);
            mTextColor = ContextCompat.getColor(getContext(), R.color.white);
        } else {
            mDrawable = ContextCompat.getDrawable(getContext(), R.drawable.common_button_light_bg);
            mTextColor = ContextCompat.getColor(getContext(), R.color.common_fm_color);
        }
        setBackgroundDrawable(mDrawable);
        setTextColor(mTextColor);
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
        invalidate();
    }

}
