package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple3;

/**
 * Description:
 * Data：2/6/2018-5:31 PM
 *
 * @author: yanzhiwen
 */
public abstract class IBox implements Cloneable {
    /**
     * //设置箱子的数量
     *
     * @param number
     */
    abstract void setNumber(int number);

    /**
     * 有多少货
     *
     * @return
     */
    abstract int getNumber();

    @Override
    protected IBox clone() throws CloneNotSupportedException {
        return (IBox) super.clone();
    }
}
