package zhiwenyan.cmccaifu.com.android2017.JavaData.recursion;

/**
 * Description: 1 1 2 3 5 8 13 f(n)=f(n-1)+f(n-2)
 * Data：2018/7/8
 * Author:Steven
 */
public class Fiboacc {
    public static int getNumber(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        }
        return getNumber(n - 1) + getNumber(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(getNumber(6));
    }
}
