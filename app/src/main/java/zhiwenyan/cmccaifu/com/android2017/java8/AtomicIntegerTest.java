package zhiwenyan.cmccaifu.com.android2017.java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 * Data：4/10/2019-10:58 AM
 *
 * @author yanzhiwen
 */
public class AtomicIntegerTest {
    private static AtomicInteger mAtomicInteger = new AtomicInteger(0);
    private static int count;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10; j++) {
                   // mAtomicInteger.incrementAndGet();
                    //System.out.println(mAtomicInteger.get());
                    System.out.println(count++);
                }
            });
        }
    }
}
