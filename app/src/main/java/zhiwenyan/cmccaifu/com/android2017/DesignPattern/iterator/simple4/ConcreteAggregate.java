package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple4;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Data：2018/6/23
 * Author:Steven
 */
public class ConcreteAggregate<T> implements Aggregate<T> {
    private List<T> list = new ArrayList<>();

    @Override
    public void add(T t) {
        list.add(t);
    }

    @Override
    public void remove(T t) {
        list.remove(t);
    }

    @Override
    public Iterator<T> iterator() {
        return new ConcreteIterator<>(list);

    }
}
