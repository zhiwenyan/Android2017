package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple2;

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

    @Override
    public IBox copy() {
        PlasticClampBox plasticClampBox = new PlasticClampBox();
        plasticClampBox.setName(name);
        plasticClampBox.setNumber(number);
        return plasticClampBox;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
