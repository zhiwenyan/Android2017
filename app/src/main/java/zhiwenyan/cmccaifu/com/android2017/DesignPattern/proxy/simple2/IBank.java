package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple2;

/**
 * Created by yanzhiwen on 2017/11/6.
 * <p>
 * 银行办理业务接口--》目标接口
 */

public interface IBank {
    /**
     * 申请办卡
     */
    Object applyBank();

    /**
     * 申请挂失
     */
    void lostBank();
}

