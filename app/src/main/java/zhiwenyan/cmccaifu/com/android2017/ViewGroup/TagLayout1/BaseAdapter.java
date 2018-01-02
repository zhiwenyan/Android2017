package zhiwenyan.cmccaifu.com.android2017.ViewGroup.TagLayout1;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description:
 * Data：12/28/2017-4:11 PM
 *
 * @author: yanzhiwen
 */
public abstract class BaseAdapter {
    //有多少个条目
    protected abstract int getCount();

    //返回当前的View
    protected abstract View getView(int position, ViewGroup parent);

    //观察者设计模式及时更新
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    public void registerDataSetObserver(DataSetObserver observer) {

    }
}
