package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple2;


import zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple2.iterator.Iterator;

/**
 * Created by yanzhiwen on 11/10/2017.
 */

public interface Aggregate<T> {

    Iterator<T> iterator();
}
