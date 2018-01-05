package zhiwenyan.cmccaifu.com.android2017.rxjava;

import rx.Observable;
import rx.Subscriber;

/**
 * Description:
 * Data：1/5/2018-6:10 PM
 *
 * @author: yanzhiwen
 */
public class RxJava {

    public static void main(String[] args) {
        create();

    }

    /**
     * create操作符
     */
    public static void create() {
        //第一步：创建被观察
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World");
                subscriber.onCompleted();
            }
        });
        //第二步：创建观察者
        Subscriber subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError()" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext()" + s);

            }
        };
        //第三步：订阅
        observable.subscribe(subscriber);
    }
}
