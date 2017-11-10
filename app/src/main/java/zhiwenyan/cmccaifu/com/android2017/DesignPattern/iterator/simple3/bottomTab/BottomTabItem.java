package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.bottomTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by yanzhiwen on 11/10/2017.
 * <p>
 * 基类 BottomTabItem
 */

public abstract class BottomTabItem {
    //布局id，Context，最好采用Builder设计模式
    //底部的子Item
    private View mTabItemView;
    private int mLayoutId;
    private Context mContext;

    public BottomTabItem(int layoutId, Context context) {
        mLayoutId = layoutId;
        mContext = context;
    }

    /**
     * @return
     */
    public View getTabView() {
        if (mTabItemView == null) {
            mTabItemView = LayoutInflater.from(mContext).inflate(mLayoutId, null);
        }
        initLayout();
        return mTabItemView;
    }

    protected <T> T findViewById(int id) {
        return (T) mTabItemView.findViewById(id);
    }

    /**
     * 是否选中当前Item
     *
     * @param selected
     */
    protected abstract void setSelected(boolean selected);

    protected abstract void initLayout();


}
