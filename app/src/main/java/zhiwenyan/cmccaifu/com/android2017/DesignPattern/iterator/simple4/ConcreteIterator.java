package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple4;

import java.util.List;

/**
 * Description:
 * Data：2018/6/23
 * Author:Steven
 */
public class ConcreteIterator<T> implements Iterator<T> {
    private List<T> list;
    private int cursor = 0;

    public ConcreteIterator(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return cursor != list.size();
    }

    @Override
    public T next() {
        T obj = null;
        if (this.hasNext()) {
            obj = this.list.get(cursor++);
        }
        return obj;
    }
}
