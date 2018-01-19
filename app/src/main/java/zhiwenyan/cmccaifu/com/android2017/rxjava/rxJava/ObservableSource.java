package zhiwenyan.cmccaifu.com.android2017.rxjava.rxJava;

/**
 * Description:
 * Data：1/17/2018-2:23 PM
 *
 * @author: yanzhiwen
 */
public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);

//    void subscribe(Consumer<T> consumer);
}
