package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.iterator;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.bottomTab.BottomTabItem;

/**
 * Created by yanzhiwen on 11/10/2017.
 */

public class ListIterator<T extends BottomTabItem> implements TabIterator {
    private List<T> mBottomTabItems;
    private int index = 0;

    public ListIterator() {
        mBottomTabItems = new ArrayList<>();
    }

    public void addItem(T item) {
        mBottomTabItems.add(item);
    }

    @Override
    public BottomTabItem next() {
        return mBottomTabItems.get(index++);
    }

    @Override
    public boolean hashNext() {
        return index < mBottomTabItems.size();
    }
}
