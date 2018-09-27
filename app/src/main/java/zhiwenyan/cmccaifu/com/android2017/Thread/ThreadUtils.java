package zhiwenyan.cmccaifu.com.android2017.Thread;

/**
 * Description:
 * Data：9/12/2018-5:06 PM
 *
 * @author yanzhiwen
 */
public class ThreadUtils {



    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
            }
        }).start();

    }
}
