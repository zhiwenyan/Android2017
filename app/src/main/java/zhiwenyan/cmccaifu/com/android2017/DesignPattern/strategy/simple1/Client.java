package zhiwenyan.cmccaifu.com.android2017.DesignPattern.strategy.simple1;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class Client {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();
        float money = financeManager.finance(6, 10000, FinanceManager.Finance.ZHI_FU_BAO);
        System.out.println("money===" + money);

    }
}
