package zhiwenyan.cmccaifu.com.android2017.JavaData.recursion;

/**
 * Description: 递归 自己调自己
 * Data：2018/7/8
 * Author:Steven
 */
public class recursion {


    public static void main(String[] args) {
        test(100);
    }

    public static void test(int n) {
        if (n == 0) {
            return;
        }
        System.out.println(n);
        test(n - 1);

    }
}
