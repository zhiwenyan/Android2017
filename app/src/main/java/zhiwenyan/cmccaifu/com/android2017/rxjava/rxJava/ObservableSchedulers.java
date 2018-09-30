package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

/**
 * Description:
 * Data：2/8/2018-1:51 PM
 *
 * @author: yanzhiwen
 */
public class ObservableSchedulers<T> extends Observable<T> {
    private Observable<T> source;
    private Schedulers schedulers;

    public ObservableSchedulers(Observable<T> source, Schedulers io) {
        this.source = source;
        this.schedulers = io;

    }

    @Override
    void subscribeActual(Observer<T> observer) {
        schedulers.scheduleDirect(new SubscribeTask(observer));
    }

    private class SubscribeTask implements Runnable {
        final Observer<T> observer;

        public SubscribeTask(Observer<T> observer) {
            this.observer = observer;
        }
        //这个run()方法执行在子线程中
        @Override
        public void run() {
            source.subscribe(observer);
        }
    }
}
