package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple4;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class StevenListView extends ScrollView {
    private LinearLayout mContainer;
    private ListAdapter mListAdapter; //目标接口

    public StevenListView(Context context) {
        this(context, null);
    }

    public StevenListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StevenListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContainer = new LinearLayout(context);
        mContainer.setOrientation(LinearLayout.VERTICAL);
        addView(mContainer, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public void addView(View child) {
        mContainer.addView(child);
    }

    public void setAdapter(ListAdapter adapter) {
        mListAdapter = adapter;
        int count = mListAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View childView = mListAdapter.getView(i, mContainer);
            mContainer.addView(childView);
        }
    }
}
