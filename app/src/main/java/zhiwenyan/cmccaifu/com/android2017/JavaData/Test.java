package zhiwenyan.cmccaifu.com.android2017.JavaData;

/**
 * Description:
 * Data：2018/7/7
 * Author:Steven
 */
public final class Test {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("回收");
    }

    public static void main(String[] args) {
        Test test1 = new Test();
        Test test2 = new Test();
        System.out.println(0/0.0);
        System.out.println(test1.hashCode());
        System.out.println(test1.hashCode()>>>16);
        System.out.println((test1.hashCode()>>>16)^(test1.hashCode()));
        System.out.println(((test1.hashCode()>>>16)^(test1.hashCode()))&7);
    //    System.out.println(1/0);

    }
}
