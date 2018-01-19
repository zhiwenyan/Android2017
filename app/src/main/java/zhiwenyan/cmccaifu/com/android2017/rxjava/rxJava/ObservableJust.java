package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

/**
 * Description:
 * Data：1/17/2018-2:36 PM
 *
 * @author: yanzhiwen
 */
public class ObservableJust<T> extends Observable<T> {
    private T item;

    public ObservableJust(T item) {
        this.item = item;
    }

    @Override
    void subscribeActual(Observer<T> observer) {
        //第二步
        ScalarDisposable scalarDisposable = new ScalarDisposable(observer, item);
        observer.onSubscribe();
        scalarDisposable.run();
    }

    private class ScalarDisposable {
        private Observer<T> observer;
        private T item;

        public ScalarDisposable(Observer<T> observer, T item) {
            this.observer = observer;
            this.item = item;
        }

        public void run() {
            //代理对象,why？方便代码扩展。
            try {
                //第三步 observer-》MapObserver
                observer.onNext(item);
                observer.onComplete();
            } catch (Throwable throwable) {
                observer.onError(throwable);
            }
        }
    }
}
