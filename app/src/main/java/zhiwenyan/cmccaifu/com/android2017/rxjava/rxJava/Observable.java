package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;


/**
 * Description:被观察者
 * Data：1/17/2018-2:23 PM
 *
 * @author: yanzhiwen
 */
public abstract class Observable<T> implements ObservableSource<T> {
    //方法泛型一定要声明
    public static <T> Observable<T> just(T t) {
        return onAssembly(new ObservableJust<>(t));
    }

    public final <R> Observable<R> map(Function<T, R> mapper) {
        return onAssembly(new ObservableMap<>(this, mapper));
    }

    private static <T> Observable<T> onAssembly(Observable<T> source) {
        return source;
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    public void subscribe(Consumer<T> onNext) {
        subscribe(onNext, null, null);
    }

    private void subscribe(Consumer<T> onNext, Consumer<T> error, Consumer<T> complete) {
        //代理设计模式
        subscribe(new LambdaObserver(onNext));
    }

    abstract void subscribeActual(Observer<T> observer);
}
