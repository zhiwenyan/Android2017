package zhiwenyan.cmccaifu.com.android2017.rxjava;

/**
 * Description: RxJava 调度器
 * 什么是调度器？
 * 调度器是RxJava以一种极其简单的方式解决多线程的问题机制
 * 调度器的种类:
 * io 这个调度器用于IO操作
 * computation 这个是计算工作默认的调度器，它与IO操作无关
 * immediate   这个调度器允许你立即在当前线程执行指定的工作
 * newThread   它为指定任务启动一个新的线程
 * trampoline  调度器将会处理队列，并运行队列的每一个任务
 * AndroidSchedulers是什么？
 * AndroidSchedulers是在RxAndroid库提供在安卓一个平台的调度器（指定观察者在主线程）
 *  阻塞操作和非阻塞操作
 * SubscribeOn and observeOn?
 * SubscribeOn()方法用来于每个Observable对象
 * ObserveOn()方法 用来于每个Subscriber(Observer)对象
 *

 * Data：1/8/2018-3:24 PM
 *
 * @author: yanzhiwen
 */
public class RxJavaSchedulers {
    public static void main(String[] args) {

    }
}
