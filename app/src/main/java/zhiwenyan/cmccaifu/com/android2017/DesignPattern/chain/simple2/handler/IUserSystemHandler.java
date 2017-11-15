package zhiwenyan.cmccaifu.com.android2017.DesignPattern.chain.simple2.handler;

/**
 * Created by yanzhiwen on 11/15/2017.
 */

public interface IUserSystemHandler<T extends AbsSystemHandler> {
    public void nextHandler(T absSystemHandler);
}
