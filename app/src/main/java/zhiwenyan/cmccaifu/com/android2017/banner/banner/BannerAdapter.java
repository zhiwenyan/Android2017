package zhiwenyan.cmccaifu.com.android2017.banner.banner;

import android.view.View;

/**
 * Created by zhiwenyan on 6/1/17.
 */

public abstract class BannerAdapter {
    /**
     * 根据位置获取ViewPager的子View
     *
     * @param position
     * @return
     */
    public abstract View getView(int position);

    /**
     * 返回数量
     *
     * @return
     */
    public abstract int getCount();
}
