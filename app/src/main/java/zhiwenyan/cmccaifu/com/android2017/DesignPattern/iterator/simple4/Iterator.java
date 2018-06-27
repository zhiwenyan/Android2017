package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple4;

/**
 * Description:
 * Data：2018/6/23
 * Author:Steven
 */
public interface Iterator<T> {

    /**
     * 是否还有下一个元素
     * <p>
     * * @return
     */
    boolean hasNext();

    /**
     * 返回当前的元素并将位置移至下一位
     *
     * @return
     */
    T next();
}
