package zhiwenyan.cmccaifu.com.android2017.gc;

/**
 * Description:
 * Data：5/24/2018-3:53 PM
 *
 * @author yanzhiwen
 */
public class Test1 {

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        a.instance = b;
        b.instance = a;
        a = null;
        b = null;

        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class A {
        public B instance;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalizeA");
        super.finalize();
    }

    public static class B {
        public A instance;

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalizeB");
            super.finalize();
        }
    }
}
