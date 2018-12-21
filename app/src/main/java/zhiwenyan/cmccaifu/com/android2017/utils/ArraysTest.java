package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Description:
 * Data：8/3/2018-2:10 PM
 *
 * @author yanzhiwen
 */
public class ArraysTest {
    static String aa = "";
    public boolean flag;

    private void test() {
//        AppExecutors appExecutors = new AppExecutors();
//        appExecutors.networkIO().execute(new java.lang.Runnable() {
//            @Override
//            public void run() {
//                flag = false;
//                //doSomething
//            }
//        });
    }

    public boolean getFlag() {
        return flag;
    }


    public static void main(String[] args) {

//        int a[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 10};
//        System.out.println(Arrays.binarySearch(a, 5));
//        System.out.println(Arrays.copyOf(a, 10)[5]);
//        Arrays.sort(a);
//        System.out.println(a[a.length - 1]);
//        List<Integer> list = Arrays.asList(1, 3, 4, 5, 6);
//        System.out.println(Collections.binarySearch(list, 3));
//        System.out.println(list.get(1));
//        System.out.println("a=" + ArraysTest.aa);
        ArraysTest test = new ArraysTest();
        test.test();
//        try {
//            Thread.sleep(3000);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        for (int i = 0; i < 100; i++) {
            new Thread(new java.lang.Runnable() {
                @Override
                public void run() {
                    test.flag = false;

                }
            }).start();

        }

        System.out.println(test.flag);

    }
}
