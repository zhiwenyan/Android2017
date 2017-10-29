package zhiwenyan.cmccaifu.com.android2017.banner.适配器模式;

/**
 * Created by zhiwenyan on 6/1/17.
 * 类适配器模式
 */

/**
 * Target角色
 */
interface FiveVolt {
    int getVolt5();
}

/**
 * Adaptee角色,需要被转换的对象
 */
class Volt220 {
    public int getVolt220() {
        return 220;
    }
}

// adapter角色
public class ClassAdapter extends Volt220 implements FiveVolt {

    @Override
    public int getVolt5() {
        return 5;
    }

    /*
    Target角色给出了需要的目标接口，而Adaptee类则是需要被转换的对象。
    Adapter则是将Volt220转换成Target的接口。对应的是Target的目标是要获取5V的输出电压，
    而Adaptee即正常输出电压是220V，此时我们就需要电源适配器类将220V的电压转换为5V电压，
    解决接口不兼容的问题。
     */
    public static void main(String[] args) {
        ClassAdapter adapter = new ClassAdapter();
        System.out.println("输出电压 : " + adapter.getVolt5());
    }
}
