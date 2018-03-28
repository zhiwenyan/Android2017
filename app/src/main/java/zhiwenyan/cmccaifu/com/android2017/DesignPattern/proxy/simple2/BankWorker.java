package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple2;

/**
 * Created by yanzhiwen on 2017/11/6.
 * <p>
 * 银行工作人员-----》代理对象  实现目标接口
 */

public class BankWorker implements IBank {
    private Man mMan;

    /**
     * 持有代理的对象
     *
     * @param man
     */

    public BankWorker(Man man) {
        mMan = man;
    }

    @Override
    public Object applyBank() {
        System.out.println("受理");
        mMan.applyBank();
        System.out.println("完毕");
        return mMan;
    }

    @Override
    public void lostBank() {

    }
}
