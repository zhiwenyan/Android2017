package zhiwenyan.cmccaifu.com.android2017.rxjava;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

/**
 * Description:
 * Data：1/5/2018-6:10 PM
 *
 * @author: yanzhiwen
 */
public class RxJava {

    public static void main(String[] args) {
        //创建操作符
        create();
        just();
        from();
        Range();
        interval();
        //转换操作符
        map();
        flatMap();
        GroupBy();
        scan();
        //过滤操作符
        Debounce();
        distinct();
        elementAt();
        last();
        //组合操作符
        //Merge,join,startWith,Concat zip
        zip();
        merge();
        startWith();
        join();
        //错误处理操作符 catch() Retry()


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


    /**
     * just操作符
     */
    private static void just() {
        // just(T...)将传入的参数依次发送
        Observable.just("just--》Hello RxJava")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext()" + s);
                    }
                });
    }

    /**
     * from 操作符
     */
    public static void from() {
        System.out.println("-----from-----");
        //from(T[])/from(Iterable<? extends T>)将传入的数组或者Iterable拆分成Java对象依次发送
        String[] parameters = {"One", "Two", "Three"};
        Observable.from(parameters).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext()" + s);
            }
        });
    }

    public static void interval() {
        System.out.println("---interval---");
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("aLong=" + aLong);
                    }
                });
    }

    public static void Range() {
        System.out.println("-----Range----");
        Observable.range(0, 10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                        System.out.println("onNext()--" + integer);
                    }
                });
    }

    public static void map() {
        Observable.just(123)
                //map转换 Integer转换成String 对象的转换
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return integer + "";
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext()" + s);
            }
        });
    }

    public static void flatMap() {
        System.out.println("-----flatMap----");
        //一对多个数据对象
        Observable.just(1, 2, 3, 4, 5)
                .flatMap(new Func1<Integer, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Integer integer) {
                        return Observable.just(integer + "");
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext()" + s);
            }
        });
    }

    public static void GroupBy() {
        System.out.println("----groupBy---");
        Observable.just(1, 2, 3, 4, 5)
                //数据的分组
                .groupBy(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer % 2;
                    }
                }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final GroupedObservable<Integer, Integer> observable) {
                observable.subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer data) {
                        System.out.println("group：" + observable.getKey() + "data:" + data);
                    }
                });

            }
        });
    }

    public static void scan() {
        System.out.println("---scan---");
        Observable.just(1, 2, 3, 4, 5)
                //scan()对一个序列的数据应用一个函数，并将这个函数的结果发射出去作为下个数据应用合格函数时的第一个参数使用。
                .scan(new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer, Integer integer2) {
                        return integer + integer2;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer + "");
            }
        });
//        Observable.just(1, 2, 3, 4, 5)
//                .scan((x,y)->x+y)
//                .subscribe(x-> System.out.println(x));

    }

    public static void Debounce() {
        System.out.println("---Debounce---");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        subscriber.onNext(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        subscriber.onError(e);
                    }
                }
            }
        }).debounce(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("onNext()->" + integer);
                    }
                });
    }

    public static void distinct() {
//        distinct()的过滤规则是只允许还没有发射过的数据通过，所有重复的数据项都只会发射一次。
        Observable.just(2, 1, 2, 2, 3, 4, 3, 4, 5, 5)
                .distinct()
                //distinct() 过滤通过的元素
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        //只允许大于2的数通过
                        return integer > 2;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                System.out.println(i + " ");
            }
        });
    }

    public static void elementAt() {
        System.out.println("---elementAt---");
        //elementAt只允许通过制定位置的元素
        Observable.just(2, 1, 2, 2, 3, 4, 3, 4, 5, 5)
                .elementAt(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer + "");
                    }
                });
    }

    public static void last() {
        //last 取最后一个元素
        Observable.just(2, 1, 2, 2, 3, 4, 3, 4, 5, 5)
                .last()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer + "");
                    }
                });
    }

    private static void zip() {
        System.out.println("---zip---");
        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<Integer> o2 = Observable.just(1, 2, 3, 14);
        //用来合并两个Observable发射的数据项，根据Func2函数生成一个新的值并发射出去，
        //当其中一个Observable发送的数据结束或者出现异常后，另外一个Observable送的数据结束或者出现异常
        Observable.zip(o1, o2, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    public static void merge() {
        System.out.println("---merge---");
        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<Integer> o2 = Observable.just(1, 2, 3, 8);
        //将两个Observable发射的事件序列组合并成一个事件序列，就像是一个Observable发射一样，
        //可以简单的理解为将两个Observable合并成了一个Observable
        Observable.merge(o1, o2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }

    public static void startWith() {
        System.out.println("---startWith---");
        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<Integer> o2 = Observable.just(1, 2, 3, 8);
        //用在源Observable发射的数据前插入数据，使用startWith(Interable<T>)
        o1.startWith(o2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    public static void combineLatest() {
        Observable<Integer> o1 = Observable.just(1, 2, 3);
        Observable<Integer> o2 = Observable.just(1, 2, 3, 8);
    }

    public static void join() {
        //如果一个Observable发射了一条数据，只要在另一个Observable发射的数据定义的时间窗口内
        // 就结合两个Observable发射的数据，然后发射结合后的数据。
        //目标Observable
        Observable<Integer> obs1 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i < 5; i++) {
                    subscriber.onNext(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //join
        Observable.just("srcObs-")
                .join(obs1,
                        //接受从源Observable发射来的数据，并返回一个Observable，
                        //这个Observable的生命周期决定了源Observable发射出来数据的有效期
                        new Func1<String, Observable<Long>>() {
                            @Override
                            public Observable<Long> call(String s) {
                                return Observable.timer(3000, TimeUnit.MILLISECONDS);
                            }
                        },
                        //接受从目标Observable发射来的数据，并返回一个Observable，
                        //这个Observable的生命周期决定了目标Observable发射出来数据的有效期
                        new Func1<Integer, Observable<Long>>() {
                            @Override
                            public Observable<Long> call(Integer integer) {
                                return Observable.timer(2000, TimeUnit.MILLISECONDS);
                            }
                        },
                        //接收从源Observable和目标Observable发射来的数据，并返回最终组合完的数据
                        new Func2<String, Integer, String>() {
                            @Override
                            public String call(String str1, Integer integer) {
                                return str1 + integer;
                            }
                        })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        System.out.println("join" + o);
                    }
                });
        //groupJoin
        Observable.just("srcObs-").groupJoin(obs1,
                new Func1<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(String s) {
                        return Observable.timer(3000, TimeUnit.MILLISECONDS);
                    }
                },
                new Func1<Integer, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Integer integer) {
                        return Observable.timer(2000, TimeUnit.MILLISECONDS);
                    }
                },
                new Func2<String, Observable<Integer>, Observable<String>>() {
                    @Override
                    public Observable<String> call(final String s, Observable<Integer> integerObservable) {
                        return integerObservable.map(new Func1<Integer, String>() {
                            @Override
                            public String call(Integer integer) {
                                return s + integer;
                            }
                        });
                    }
                })
                .subscribe(new Action1<Observable<String>>() {
                    @Override
                    public void call(Observable<String> stringObservable) {
                        stringObservable.subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                System.out.println("groupJoin:" + s);
                            }
                        });
                    }
                });
    }
    

}
