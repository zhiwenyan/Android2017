package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Flyweight.simple1;

import android.os.Message;

/**
 * Description:
 * Data：2/13/2018-4:52 PM
 *
 * @author: yanzhiwen
 */
public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Ticket ticket = TicketFactory.getTicket("上海", "南昌");
            double price = ticket.getPrice();
            System.out.println("price==" + price);
        }
        Message.obtain();
    }
}
