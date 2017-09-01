package zhiwenyan.cmccaifu.com.android2017.indicatorViewPager;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yanzhiwen on 2017/8/31.
 */

public abstract class IndicatorAdapter {
    //显示的条目
    public abstract int getCount();

    //获取当前的View
    public abstract View getView(int position, ViewGroup parent);

    public void highIndicator(View view) {

    }

    //重置当前的位置
    public void restoreIndicator(View view) {
    }

    public View getBottomTrackView() {
        return null;
    }

}
