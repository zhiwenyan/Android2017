package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

/**
 * Description:
 * Data：1/17/2018-4:26 PM
 *
 * @author: yanzhiwen
 */
public class LambdaObserver<T> implements Observer<T>{
    private Consumer<T> mOnNext;

    public LambdaObserver(Consumer<T> onNext) {
        mOnNext = onNext;
    }

    @Override
    public void onSubscribe() {

    }

    @Override
    public void onNext(T t) {
        mOnNext.onNext(t);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
