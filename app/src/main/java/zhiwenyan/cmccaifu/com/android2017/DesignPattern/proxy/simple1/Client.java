package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple1;

/**
 * Created by yanzhiwen on 2017/11/6.
 */

public class Client {
    public static void main(String[] args) {
        Man man=new Man("Steven");
        BankWorker bankWorker=new BankWorker(man);
        bankWorker.applyBank();
    }
}
