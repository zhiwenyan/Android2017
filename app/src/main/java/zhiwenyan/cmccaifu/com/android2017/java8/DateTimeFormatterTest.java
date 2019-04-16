package zhiwenyan.cmccaifu.com.android2017.java8;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * Data：4/10/2019-9:33 AM
 *
 * @author yanzhiwen
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class DateTimeFormatterTest {

    //会引起线程并发的问题
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formDate(Date date) throws ParseException {
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return sdf.parse(strDate);
    }

    //基于JDK1.8的DateTimeFormatter
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formDate2(LocalDateTime date) throws ParseException {
        return dtf.format(date);
    }

    public static LocalDateTime parse2(String strDate) throws ParseException {
        return LocalDateTime.parse(strDate, dtf);
    }

    public static void main(String[] args) throws InterruptedException, ParseException {
        //  System.out.println(sdf.format(new Date()));
        System.out.println(dtf.format(LocalDateTime.now()));
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        System.out.println(parse2(formDate2(LocalDateTime.now())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
