package zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory;


import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple5.DiskIOHandler;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple5.IOHandler;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple5.MemoryIOHandler;
import zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple5.PreferencesIOHandler;

/**
 * Created by yanzhiwen on 2017/9/30.
 * <p>
 * 工厂设计模式--抽象方法模式
 */

public class IOHandlerFactory {

    private static volatile IOHandlerFactory mInstance;

    private IOHandler mMemoryIOHandler, mDiskIOHandler, mPreferecesIOHandler;

    public static IOHandlerFactory getmInstance() {
        if (mInstance == null) {
            synchronized (IOHandlerFactory.class) {
                if (mInstance == null) {
                    mInstance = new IOHandlerFactory();
                }
            }
        }
        return mInstance;
    }

    public static IOHandler createIOHandler(Class<? extends IOHandler> clz) {
        try {
            return clz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new PreferencesIOHandler();
    }

    public IOHandler getMemoryIOHandler() {
        if (mMemoryIOHandler == null) {

            return createIOHandler(MemoryIOHandler.class);
        }
        return mMemoryIOHandler;
    }

    public IOHandler getDiskIOHandler() {
        if (mDiskIOHandler == null) {

            return createIOHandler(DiskIOHandler.class);
        }
        return mDiskIOHandler;
    }

    public IOHandler getPreferencesIOHandler() {
        if (mPreferecesIOHandler == null) {
            return createIOHandler(PreferencesIOHandler.class);
        }
        return mPreferecesIOHandler;
    }

    public IOHandler getDefaultIOHandler() {
        return getPreferencesIOHandler();
    }
}
