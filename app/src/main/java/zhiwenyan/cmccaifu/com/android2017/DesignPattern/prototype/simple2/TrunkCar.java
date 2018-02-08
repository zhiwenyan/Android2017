package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple2;

/**
 * Description: 卡车
 * Data：2/6/2018-5:37 PM
 *
 * @author: yanzhiwen
 */
public class TrunkCar {
    public IBox box;

    public void addBox(IBox box) {
        this.box = box;
    }

    public IBox remove() {
        return box;
    }

}
