package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1;


import zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple1.iterator.Iterator;

/**
 * Created by yanzhiwen on 11/10/2017.
 */

public interface Aggregate<T> {

    Iterator<T> iterator();
}
