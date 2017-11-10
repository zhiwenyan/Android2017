package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.iterator;

import zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple3.bottomTab.BottomTabItem;

/**
 * Created by yanzhiwen on 11/10/2017.
 */

public interface TabIterator {
    BottomTabItem next();

    boolean hashNext();
}
