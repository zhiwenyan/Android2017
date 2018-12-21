package zhiwenyan.cmccaifu.com.android2017.java8;

/**
 * Description:
 * Data：12/19/2018-2:43 PM
 *
 * @author yanzhiwen
 */
public class Java8Test {
    public static void main(String[] args) {
        int a = 1;
        String url ="<img src=\"http://mobile.qq.com/qrcode?url=http:/jh.chinambpc.com/wxpay?json=%7B%22requestTime%22%3A%221545271920%22%2C%22orderId%22%3A51414416%2C%22sign%22%3A%22f4666bee71d9b5ccf34dd09d27666140%22%2C%22serialno%22%3A%22580HK2018122010115913508%22%7D\"/>";

        String imgUrl = url.split("url=")[1];
        System.out.println(imgUrl);
        System.out.println(imgUrl.substring(0, imgUrl.length() - 3));
    }

    private static <T> void varvag(T... a) {
        System.out.println(a[1]);
    }
}
