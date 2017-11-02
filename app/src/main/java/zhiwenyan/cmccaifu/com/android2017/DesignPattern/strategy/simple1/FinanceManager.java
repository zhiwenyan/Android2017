package zhiwenyan.cmccaifu.com.android2017.DesignPattern.strategy.simple1;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class FinanceManager {
    public enum Finance {
        ZHI_FU_BAO, REN_ZHONG //还有很多，会导致这个类越来越大，每种理论
    }

    public float zhifubaoFinance(int month, int money) {
        switch (month) {
            case 3:
                return money + money * 0.047f / 12;
            case 6:
                return money + money * 0.05f / 12;
            case 12:
                return money + money * 0.06f / 12;
        }
        throw new IllegalArgumentException("月份不对");
    }

    public float renzhongFinance(int month, int money) {
        switch (month) {
            case 3:
                return money + money * 0.047f / 12 * money;
            case 6:
                return money + money * 0.05f / 12 * money;
            case 12:
                return money + money * 0.06f / 12 * money;
        }
        throw new IllegalArgumentException("月份不对");
    }

    public float finance(int month, int money, Finance finance) {
        switch (finance) {
            case ZHI_FU_BAO:
                return zhifubaoFinance(month, money);
            case REN_ZHONG:
                return renzhongFinance(month, money);
        }
        return 0f;
    }
}
