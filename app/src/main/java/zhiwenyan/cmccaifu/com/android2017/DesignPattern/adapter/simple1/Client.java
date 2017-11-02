package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple1;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class Client {
    public static void main(String[] args) {
        RMBAdaptee adaptee = new RMBAdaptee(100.0f);
        adaptee.getRMB();


        adaptee.getUsb();
    }
}
