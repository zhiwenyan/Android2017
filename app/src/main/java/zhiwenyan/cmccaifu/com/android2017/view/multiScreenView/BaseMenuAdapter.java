package zhiwenyan.cmccaifu.com.android2017.view.multiScreenView;

import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * Data：12/29/2017-3:01 PM
 *
 * @author: yanzhiwen
 */
public abstract class BaseMenuAdapter {
    //注册订阅放到集合中
    private MenuObserver mMenuObserver;

    public void registerDataSetObserver(MenuObserver observer) {
        mMenuObserver = observer;
    }

    public void unregisterDataSetObserver(MenuObserver observer) {
        mMenuObserver = null;
    }

    /**
     * notifyDataChanged()
     **/
    public void closeMenu() {
        if (mMenuObserver != null) {
            mMenuObserver.closeMenu();
        }
    }

    //获取总共多少条
    public abstract int getCount();

    //获取当前的View
    public abstract View getView(int position, ViewGroup view);

    //获取当前的菜单内容
    public abstract View getMenuView(int position, ViewGroup view);

    public void openMenu(int position, View tabView) {
    }

    public void closeMenu(View tabView) {
    }


}
