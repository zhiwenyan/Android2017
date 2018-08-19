package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Created by yanzhiwen on 2017/11/3.
 * <p>
 * <p>
 * 3. 题干：编写程序，完成以下功能：
 * （1）设计一个 Array2 类，添加一个整型数组作为其数据成员，添加构造方法以对数组赋初值；
 * （2）为 Array2 类添加数组的求和方法，添加返回求和值的方法；
 * （3）编写 Application 程序，利用 Array2 计算数组的求和值并输出。
 */
class A {
    public void a() {
        System.out.println("A");
    }
}

class B extends A {
    public void b() {
        System.out.println("B");
    }
}

public class Application {
    public static int index;

    public Application(int index) {
        this.index = index;
        System.out.println(index);
    }

    void test() {
        Application application = new Application(index + 1);
        System.out.println(this.index + "---000");
    }

    public static void main(String[] args) {
//        B b = new B();
//        b.b();
//        Application application = new Application(0);
//        application.test();
//        int[] a = new int[]{1, 2, 3};
//        Array array = new Array(a);
//        System.out.println("sum=" + array.sum());
//        A[] aa;
//        for (int i = 0; i < 10; i++) {
//            aa = new A[i];
//            System.out.println(aa);
//        }
//        List<Integer> mItems = new ArrayList<>();
//        mItems.add(1);
//        mItems.add(2);
////        Iterable<Integer> integers = (Iterable<Integer>) mItems.iterator();
////        integers.forEach(System.out::print);
//        String str = "abc";
//        System.out.println(str.charAt(str.indexOf("c")) == 'c');
//        System.out.println(str.getBytes());
    }

}
