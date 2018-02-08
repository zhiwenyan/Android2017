package zhiwenyan.cmccaifu.com.android2017.DesignPattern.prototype.simple3;

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
            IBox newBox = null;
            try {
                newBox = box.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            newBox.setNumber(200);
            TrunkCar trunkCar = new TrunkCar();
            trunkCar.addBox(newBox);
            list.add(trunkCar);
            box.setNumber(box.getNumber() - 200);
        }
        TrunkCar trunkCar = new TrunkCar();
        trunkCar.addBox(box);
        list.add(trunkCar);
        return list;
    }
}
