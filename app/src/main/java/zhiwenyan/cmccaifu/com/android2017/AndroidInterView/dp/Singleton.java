package zhiwenyan.cmccaifu.com.android2017.AndroidInterView.dp;

/**
 * Description:
 * Data：6/6/2018-3:27 PM
 *
 * @author yanzhiwen
 */
public class Singleton {

    private static volatile Singleton sSingleton;

    public static Singleton getInstance() {
        if (sSingleton == null) {
            synchronized (Singleton.class) {
                if (sSingleton == null) {
                    sSingleton = new Singleton();
                }
            }
        }
        return sSingleton;
    }
}
