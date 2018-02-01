package zhiwenyan.cmccaifu.com.android2017.view.switchButton;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Data：1/30/2018-3:20 PM
 *
 * @author: yanzhiwen
 */
public class SwitchButtonGroup extends LinearLayout {
    private List<SwitchButton> mSwitchButtons = new ArrayList<>();
    //被点击的位置
    private int mClickPosition;

    public SwitchButtonGroup(Context context) {
        this(context, null);
    }

    public SwitchButtonGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchButtonGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        getChildView();

    }

    private void getChildView() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof SwitchButton) {
                SwitchButton switchButton = (SwitchButton) view;
                mSwitchButtons.add(switchButton);
                setOnClick(switchButton, i);
            }
        }
    }

    /**
     * @param switchButton
     * @param position
     */
    private void setOnClick(SwitchButton switchButton, final int position) {
        switchButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = getChildCount();
                for (int i = 0; i < count; i++) {
                    View view = getChildAt(i);
                    if (view instanceof SwitchButton) {
                        SwitchButton switchButton = (SwitchButton) view;
                        if (i == position) {
                            mClickPosition = position;
                            switchButton.setSelected(true);
                        } else {
                            switchButton.setSelected(false);
                        }
                    }
                }
            }
        });
    }

    public int getClickPosition() {
        return mClickPosition;
    }
}
