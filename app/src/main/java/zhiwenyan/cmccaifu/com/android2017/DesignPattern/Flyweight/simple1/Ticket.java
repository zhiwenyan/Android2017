package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Flyweight.simple1;

import java.util.Random;

/**
 * Description:火车票
 * Data：2/13/2018-4:46 PM
 *
 * @author: yanzhiwen
 */
public class Ticket {
    String from; //从哪里
    String to; //去哪里

    /**
     * @param from
     * @param to
     */
    public Ticket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    double getPrice() {
        return new Random().nextInt(100) + 20;
    }
}
