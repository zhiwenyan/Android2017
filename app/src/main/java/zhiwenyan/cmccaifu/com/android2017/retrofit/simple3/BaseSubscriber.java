package zhiwenyan.cmccaifu.com.android2017.retrofit.simple3;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Data：1/23/2018-10:02 AM
 *
 * @author: yanzhiwen
 */
public class BaseSubscriber<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        //error 错误  网络异常  解析异常 结果处理异常
    }

    @Override
    public void onComplete() {

    }
}
