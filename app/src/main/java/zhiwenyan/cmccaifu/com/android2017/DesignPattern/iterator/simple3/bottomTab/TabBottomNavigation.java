package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.bottomTab;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.iterator.TabIterator;

/**
 * Created by yanzhiwen on 11/10/2017.
 */

public class TabBottomNavigation extends LinearLayout {
    private List<BottomTabItem> mBottomTabItems = new ArrayList<>();
    private int mCurrentIndex = -1;

    public TabBottomNavigation(Context context) {
        this(context, null);
    }

    public TabBottomNavigation(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabBottomNavigation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);

    }

    public void addTabItem(TabIterator tabItems) {
        mBottomTabItems.clear();
        //当前位置
        int index = 0;
        while (tabItems.hashNext()) {
            BottomTabItem tabItem = tabItems.next();
            mBottomTabItems.add(tabItem);
            View tabView = tabItem.getTabView();
            addView(tabView);
            LinearLayout.LayoutParams params = (LayoutParams) tabView.getLayoutParams();
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            setLayoutParams(params);
            //给条目点击事件
            setItemClickListener(tabView, index++);
        }


        mBottomTabItems.get(0).setSelected(true);
        mCurrentIndex = 0;
    }

    private void setItemClickListener(View tabView, final int position) {
        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex != position) {
                    //原来标为没有选中
                    mBottomTabItems.get(mCurrentIndex).setSelected(false);
                    mCurrentIndex = position;
                    mBottomTabItems.get(mCurrentIndex).setSelected(true);
                    //把点击的位置利用接口传出去
                }
            }
        });
    }
}
