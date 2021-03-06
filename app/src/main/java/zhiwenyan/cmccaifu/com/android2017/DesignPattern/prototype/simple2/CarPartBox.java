package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple2;

/**
 * Description:具体的出货物品--汽车的零件
 * 汽车零件的箱子
 * Data：2/6/2018-5:35 PM
 *
 * @author: yanzhiwen
 */
public class CarPartBox implements IBox {
    private int number;
    private String name;
    private String cardBrand;

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
        CarPartBox carPartBox=new CarPartBox();
        carPartBox.setName(name);
        carPartBox.setNumber(number);
        carPartBox.setCardBrand(cardBrand);
        return carPartBox;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = cardBrand;
    }

    public String getCardBrand() {
        return cardBrand;
    }
}
