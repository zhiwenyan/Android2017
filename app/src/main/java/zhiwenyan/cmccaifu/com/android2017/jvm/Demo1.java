package zhiwenyan.cmccaifu.com.android2017.jvm;

/**
 * Description:
 * Data：3/7/2018-5:28 PM
 *
 * @author: yanzhiwen
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println("Demo1的main方法");
        A a = new A();
        System.out.println(A.width);
        //类只会加载初始化一次
        A a2 = new A();
    }
}

class A extends A_Father {
    public static int width = 100;

    static {
        System.out.println("静态初始化A。");
        width = 300;
    }

    public A() {
        System.out.println("初始化A");
    }
}

class A_Father {
    static {
        System.out.println("静态初始化A_Father");

    }
}