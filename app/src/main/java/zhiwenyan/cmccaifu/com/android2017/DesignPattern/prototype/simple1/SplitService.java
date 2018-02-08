package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple1;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Data：2/6/2018-5:43 PM
 *
 * @author: yanzhiwen
 */
public class SplitService {
    public static List<TrunkCar> spliteBox(IBox box) {
        List<TrunkCar> list = new ArrayList<>();
        while (box.getNumber() > 200) {
            //要进行拆分
            if (box instanceof PlasticClampBox) {
                PlasticClampBox orderBox = (PlasticClampBox) box;
                PlasticClampBox newBox = new PlasticClampBox();
                newBox.setName(orderBox.getName());
                newBox.setNumber(200);
                TrunkCar trunkCar = new TrunkCar();
                trunkCar.addBox(newBox);
                list.add(trunkCar);
                box.setNumber(box.getNumber() - 200);
            } else if (box instanceof CarPartBox) {
                CarPartBox orderBox = (CarPartBox) box;
                CarPartBox newBox = new CarPartBox();
                newBox.setName(orderBox.getName());
                newBox.setNumber(200);
                newBox.setCardBrand(newBox.getCardBrand());
                TrunkCar trunkCar = new TrunkCar();
                trunkCar.addBox(newBox);
                list.add(trunkCar);
                box.setNumber(box.getNumber() - 200);
            }
        }
        TrunkCar trunkCar = new TrunkCar();
        trunkCar.addBox(box);
        list.add(trunkCar);
        return list;
    }
}
