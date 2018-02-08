package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple3;

import java.util.List;

/**
 * Description:
 * Data：2/6/2018-5:40 PM
 *
 * @author: yanzhiwen
 */
public class Client {

    public static void main(String[] args) {

        CarPartBox carPartBox = new CarPartBox();
        carPartBox.setNumber(500);
        carPartBox.setName("灯罩");
        carPartBox.setCardBrand("奥迪");

        //接下来要去拆分
        List<TrunkCar> trunkCars = SplitService.spliteBox(carPartBox);
        for (TrunkCar trunkCar : trunkCars) {
            System.out.println("数量：" + trunkCar.box.getNumber()+"box--"+carPartBox);
        }
    }
}
