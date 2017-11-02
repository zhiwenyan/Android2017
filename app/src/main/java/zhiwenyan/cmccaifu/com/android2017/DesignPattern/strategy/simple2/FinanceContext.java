package zhiwenyan.cmccaifu.com.android2017.DesignPattern.strategy.simple2;

/**
 * Created by yanzhiwen on 2017/11/2.
 * <p>
 * 策略模式的上下文，有点类似于Context
 * 可以获取额外的基本信息 --理财有关
 * 这个类有点多余，在实际的开发中 可以不写
 */

public class FinanceContext {
    IFinance mIFinance;

    public FinanceContext(IFinance IFinance) {
        mIFinance = IFinance;
    }

    public float finance(int month, int money) {
        return mIFinance.finance(month, money);
    }

}
