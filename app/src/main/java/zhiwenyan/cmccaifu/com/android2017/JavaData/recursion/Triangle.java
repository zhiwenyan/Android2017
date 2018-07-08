package zhiwenyan.cmccaifu.com.android2017.JavaData.recursion;

/**
 * Description: 1  3  6   10  15 n=n+(n-1)
 * Data：2018/7/8
 * Author:Steven
 */
public class Triangle {
    public void getNumber(int n) {
        int total = 0;
        while (n > 0) {
            total = total + n;
            n--;
        }
        System.out.println(total);

    }

    public int getNumberByRecursion(int n) {
        if (n == 1) {
            return 1;
        }
        return n + getNumberByRecursion(n - 1);
    }

    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        triangle.getNumber(3);
        System.out.println(triangle.getNumberByRecursion(4));
    }
}
