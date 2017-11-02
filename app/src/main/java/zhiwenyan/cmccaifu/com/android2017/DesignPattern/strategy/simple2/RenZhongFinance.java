package zhiwenyan.cmccaifu.com.android2017.DesignPattern.strategy.simple2;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class RenZhongFinance implements IFinance {
    @Override
    public float finance(int month, int money) {
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
}
