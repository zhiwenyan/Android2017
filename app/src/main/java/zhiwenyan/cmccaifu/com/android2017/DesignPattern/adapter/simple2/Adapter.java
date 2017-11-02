package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple2;

/**
 * Created by yanzhiwen on 2017/11/2.
 * <p>
 * 适配器对象  --把人民币转成美元
 *
 * 类适配
 */

public class Adapter extends RMBAdaptee implements UsbTarget {

    public Adapter(float RMB) {
        super(RMB);
    }

    @Override
    public float getUsb() {
        return getRMB() / 6.0f;
    }
}
