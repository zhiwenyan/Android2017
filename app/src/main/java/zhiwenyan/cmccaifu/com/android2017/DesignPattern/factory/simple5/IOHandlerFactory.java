package zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple5;


/**
 * Created by yanzhiwen on 2017/9/30.
 * <p>
 * 工厂设计模式--抽象方法模式 通过特定的方法返回单一的对象
 */

public class IOHandlerFactory {


    public static IOHandler createIOHandler(Class<? extends IOHandler> clz) {
        try {
            return clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new PreferencesIOHandler();
    }

    public static IOHandler getMemoryIOHandler() {
        return createIOHandler(MemoryIOHandler.class);
    }

    public static IOHandler getDiskIOHandler() {
        return createIOHandler(DiskIOHandler.class);
    }

    public static IOHandler getPreferencesIOHandler() {
        return createIOHandler(PreferencesIOHandler.class);
    }

    public static IOHandler getDefaultIOHandler() {
        return getPreferencesIOHandler();
    }
}
