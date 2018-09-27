package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;


/**
 * Description: RxJava事件的转换
 * Data：1/17/2018-5:52 PM
 * 两个属性：ObservableJust、Function
 *
 * @author: yanzhiwen
 */
public class ObservableMap<T, R> extends Observable<R> {
    //前面的Observable
    final Observable<T> source;
    //当前的转换
    final Function<T, R> mapper;

    public ObservableMap(Observable<T> source, Function<T, R> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    void subscribeActual(Observer<R> observer) {
        //第一步
        //对Observer 包裹了一层 静态代理包裹source永远是上游的Observable的对象
        //Observer代表的是下游给我们的封装好的observer对象（观察者）
        source.subscribe(new MapObserver(observer, mapper));
    }

    private class MapObserver implements Observer<T> {
        final Observer<R> observer;
        final Function<T, R> mapper;

        public MapObserver(Observer<R> observer, Function<T, R> mapper) {
            this.observer = observer;
            this.mapper = mapper;
        }


        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            //要去转换
            try {
                //第四步
                R applyR = mapper.apply(t);
                //第六步
                //把Bitmap传出去
                observer.onNext(applyR);
            } catch (Exception e) {
                observer.onError(e);
            }
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }

}

