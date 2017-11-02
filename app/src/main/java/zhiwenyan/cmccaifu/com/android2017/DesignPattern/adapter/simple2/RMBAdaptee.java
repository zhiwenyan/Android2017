package zhiwenyan.cmccaifu.com.android2017.DesignPattern.adapter.simple2;

/**
 * Created by yanzhiwen on 2017/11/2.
 */

public class RMBAdaptee {
    private float mRMB;

    public RMBAdaptee(float RMB) {
        mRMB = RMB;
    }

    /**
     * 获取人民币
     *
     * @return
     */
    public float getRMB() {
        return mRMB;
    }


}
