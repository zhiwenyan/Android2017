package zhiwenyan.cmccaifu.com.android2017.JavaData;

/**
 * Description:
 * Data：8/7/2018-3:58 PM
 *
 * @author yanzhiwen
 */
public class ReverseTest {
    String ss;

    private String reverse(String str) {
        char[] chArr = str.toCharArray();
        int middle = chArr.length / 2;
        for (int i = 0; i < middle; i++) {
            char temp = chArr[i];
            chArr[i] =  chArr[chArr.length - 1 - i];
            chArr[chArr.length - 1 - i] = temp;
        }
        return new String(chArr);

    }

    public static void main(String[] args) {
        ReverseTest reverseTest = new ReverseTest();
        System.out.println(reverseTest.reverse("aaa2222bbb"));
    }
}
