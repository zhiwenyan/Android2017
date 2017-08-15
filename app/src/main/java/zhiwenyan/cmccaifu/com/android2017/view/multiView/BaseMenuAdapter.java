package zhiwenyan.cmccaifu.com.android2017.view.multiView;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhiwenyan on 2017/8/15.
 */

public abstract class BaseMenuAdapter {
    /**
     *
     * @return
     */
    public abstract int getCount();

    /**
     *
     * @param position
     * @param parent
     * @return
     */
    public abstract View getTabView(int position, ViewGroup parent);

    /**
     *
     * @param position
     * @param parent
     * @return
     */
    public abstract View getMenuView(int position, ViewGroup parent);
}
