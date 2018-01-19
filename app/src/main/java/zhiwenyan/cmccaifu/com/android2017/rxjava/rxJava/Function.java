package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

/**
 * Description:RxJava事件转换
 * Data：1/17/2018-5:48 PM
 *
 * @author: yanzhiwen
 */
public interface Function<T, R> {
    R apply(T t);
}
