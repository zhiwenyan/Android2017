package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple2;

/**
 * Description:
 * Data：2/6/2018-5:31 PM
 *
 * @author: yanzhiwen
 */
public interface IBox {
    /**
     * //设置箱子的数量
     *
     * @param number
     */
    void setNumber(int number);

    /**
     * 有多少货
     *
     * @return
     */
    int getNumber();

    //新增一个方法 拷贝
    IBox copy();

}
