package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple3;

/**
 * Created by yanzhiwen on 2017/11/2.
 * <p>
 * 适配器对象  --把人民币转成美元
 * 对象适配
 */

public class Adapter implements UsbTarget {
    public RMBAdaptee mRMBAdaptee;

    public Adapter(RMBAdaptee rMBAdaptee) {
        this.mRMBAdaptee = rMBAdaptee;
    }

    @Override
    public float getUsb() {
        return mRMBAdaptee.getRMB() / 6.0f;
    }
}
