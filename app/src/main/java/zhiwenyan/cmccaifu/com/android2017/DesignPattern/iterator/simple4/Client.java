package zhiwenyan.cmccaifu.com.android2017.DesignPattern.iterator.simple4;

/**
 * Description:
 * Data：2018/6/23
 * Author:Steven
 */
public class Client {
    public static void main(String[] args) {
        Aggregate<String> aggregate = new ConcreteAggregate<>();
        aggregate.add("a");
        aggregate.add("b");
        aggregate.add("c");
        Iterator iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }
}
