package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

/**
 * Description:
 * Data：2/8/2018-3:11 PM
 *
 * @author: yanzhiwen
 */
public class ObserveOn<T> extends Observable<T> {
    private Observable<T> source;
    private Schedulers schedulers;

    public ObserveOn(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    void subscribeActual(Observer<T> observer) {
        //静态代理对象
        source.subscribe(new ObserverOnObserver<>(observer, schedulers));

    }

    private class ObserverOnObserver<T> implements Observer<T>, Runnable {
        private Observer<T> observer;
        private Schedulers schedulers;
        private T value;

        public ObserverOnObserver(Observer<T> observer, Schedulers schedulers) {
            this.observer = observer;
            this.schedulers = schedulers;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();

        }

        @Override
        public void onNext(T t) {
            schedulers.scheduleDirect(this);
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void run() {
            observer.onNext(value);
        }

    }

}
