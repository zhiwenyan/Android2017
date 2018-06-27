package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple4;

/**
 * Description: 容器接口
 * 容器接口负责提供创建具体迭代器角色的接口
 * Data：2018/6/23
 * Author:Steven
 */
public interface Aggregate<T> {

    void add(T t);

    void remove(T t);

    Iterator<T> iterator();

}
