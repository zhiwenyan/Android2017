package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple1;

/**
 * Description:塑料夹子的箱子
 * Data：2/6/2018-5:33 PM
 *
 * @author: yanzhiwen
 */
public class PlasticClampBox implements IBox {
    private int number;
    private String name;

    @Override
    public void setNumber(int number) {
        this.number = number;

    }

    @Override
    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
