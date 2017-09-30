package zhiwenyan.cmccaifu.com.android2017.DesignPattern.factory.simple2;

/**
 * Created by yanzhiwen on 2017/9/30.
 * <p>
 * 工厂设计模式--工厂方法模式
 *
 * 一个工厂一般生成对应的产品
 */

public class PreferencesIOFactory implements IOFactory {

    @Override
    public IOHandler createIOHandler() {
        return new PreferencesIOHandler();
    }
}
