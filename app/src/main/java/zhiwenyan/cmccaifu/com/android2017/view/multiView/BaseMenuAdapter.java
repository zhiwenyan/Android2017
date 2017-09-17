package zhiwenyan.cmccaifu.com.android2017.view.multiView;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by + on 2017/8/15.
 * <p>
 * 筛选菜单的Adapter
 */

public abstract class BaseMenuAdapter {
    /**
     * @return
     */
    public abstract int getCount();

    /**
     * @param position
     * @param parent
     * @return
     */
    public abstract View getTabView(int position, ViewGroup parent);

    /**
     * @param position
     * @param parent
     * @return
     */
    public abstract View getMenuView(int position, ViewGroup parent);

    /**
     * 菜单打开
     *
     * @param tabView
     */
    public void openMenu(View tabView) {

    }

    /**
     * 菜单关闭
     *
     * @param tabView
     */
    public void closeMenu(View tabView) {

    }
}
