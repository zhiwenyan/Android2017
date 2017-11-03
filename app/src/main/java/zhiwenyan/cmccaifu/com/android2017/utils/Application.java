package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Created by yanzhiwen on 2017/11/3.
 * <p>
 * <p>
 * 3. 题干：编写程序，完成以下功能：
 * （1）设计一个 Array 类，添加一个整型数组作为其数据成员，添加构造方法以对数组赋初值；
 * （2）为 Array 类添加数组的求和方法，添加返回求和值的方法；
 * （3）编写 Application 程序，利用 Array 计算数组的求和值并输出。
 */

public class Application {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3};
        Array array = new Array(a);
        System.out.println("sum=" + array.sum());
    }
}
