package zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple1;

/**
 * Created by yanzhiwen on 2017/9/30.
 * <p>
 * 工厂设计模式--简单工厂模式
 */

public class IOHandlerFactory {
    public enum IOType {
        MEMORY, PREFERENCES, DISK
    }

    public static IOHandler createIOHandler(IOType type) {
        switch (type) {
            case MEMORY:
                //直接返回一个对象，有的时候创建对象之后，需要初始化一些参数
                return new MemoryIOHandler();
            case PREFERENCES:
                return new PreferencesIOHandler();
            case DISK:
                return new DiskIOHandler();
            default:
                return null;
        }
    }
}
