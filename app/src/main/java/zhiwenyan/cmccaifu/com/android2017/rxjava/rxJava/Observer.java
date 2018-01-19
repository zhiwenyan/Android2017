package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

import io.reactivex.annotations.NonNull;

/**
 * Description:观察者
 * Data：1/17/2018-2:25 PM
 *
 * @author: yanzhiwen
 */
public interface Observer<T> {
    void onSubscribe();

    void onNext(@NonNull T t);

    void onError(Throwable e);

    void onComplete();
}
