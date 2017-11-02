package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple2;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class Client {
    public static void main(String[] args) {

        //第一个版本只显示人民币
        RMBAdaptee adaptee = new RMBAdaptee(100.0f);
        adaptee.getRMB();

        //第二个版需要去兼容美元，采用适配器模式
        //角色：需要适配的接口Target（美元），适配器对象（adapter）
        Adapter adapter = new Adapter(100f);
        adapter.getUsb();
    }
}
