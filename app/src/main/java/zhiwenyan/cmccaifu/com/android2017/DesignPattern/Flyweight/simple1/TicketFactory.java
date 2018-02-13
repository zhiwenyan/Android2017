package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Flyweight.simple1;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:  火车票的查询工厂
 * <p>
 * Data：2/13/2018-4:45 PM
 *
 * @author: yanzhiwen
 */
public class TicketFactory {
    //做一个缓存
    static Map<String, Ticket> sTicketMap = new HashMap<>();

    /**
     * @param from
     * @param to
     * @return
     */
    public static Ticket getTicket(String from, String to) {
        String key = from + "-" + to;
        Ticket ticket = sTicketMap.get(key);
        if (ticket != null) {
            return ticket;
        }
        ticket = new Ticket(from, to);
        sTicketMap.put(key, ticket);
        return ticket;
    }
}
