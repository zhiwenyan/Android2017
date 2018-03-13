package zhiwenyan.cmccaifu.com.android2017.view.gesture;

/**
 * Description:
 * Data：3/5/2018-4:33 PM
 *
 * @author: yanzhiwen
 */
public interface GesturePasswordViewListener {
    /**
     * 解锁成功调用
     */
    void onSuccess();

    /**
     * 错误调用
     */
    void onError();

    /**
     * 解锁失败调用
     */
    void onFailure();
}
