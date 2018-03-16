package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.interceptor;

/**
 * Description:
 * Data：3/15/2018-5:56 PM
 *
 * @author: yanzhiwen
 */
public class TestIndex {
    public static int index;

    public TestIndex(int index) {
        this.index = index;
    }

    public static void main(String[] args) {
        TestIndex testIndex = new TestIndex(index + 1);
        System.out.println(testIndex.index);
    }
}
