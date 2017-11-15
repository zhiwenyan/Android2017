package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple2.handler;

/**
 * Created by yanzhiwen on 11/15/2017.
 * <p>
 * 责任链设计模式  抽象处理者的角色
 */

public  abstract class AbsSystemHandler implements IUserSystemHandler,IUserSystem {
    private  AbsSystemHandler mNextHandler;

    public void setNextHandelr(AbsSystemHandler nextHandler) {
        mNextHandler = nextHandler;
    }

    @Override
    public void nextHandler(AbsSystemHandler nextHandler) {
        this.mNextHandler=nextHandler;
    }

    public AbsSystemHandler getNextHandler() {
        return mNextHandler;
    }
}
