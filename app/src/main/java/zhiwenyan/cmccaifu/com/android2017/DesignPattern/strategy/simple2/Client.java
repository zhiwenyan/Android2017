package zhiwenyan.cmccaifu.com.android2017.DesignPattern.strategy.simple2;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class Client {
    public static void main(String[] args) {
        //这个就采用了策略设计模式，说白了 就是在原来的基础上进行了分离
        IFinance finance = new RenZhongFinance();
        float money = finance.finance(3, 10000);
        System.out.println(money);


        FinanceContext financeContext = new FinanceContext(finance);
        float money1 = financeContext.finance(3, 10000);
        System.out.println(money1);
    }
}
