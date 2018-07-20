package zhiwenyan.cmccaifu.com.android2017.Enum;

/**
 * Description:遍历、switch 等常用操作
 * Data：7/19/2018-2:01 PM
 *
 * @author yanzhiwen
 */

public class Test {
    public static void main(String[] args) {
        for (EnumTest e : EnumTest.values()) {
            System.out.println(e.toString());
        }
        System.out.println("EnumTest.FRI 的 value = " + EnumTest1.FRI.getValue());

        System.out.println("----------------我是分隔线------------------");

        EnumTest test = EnumTest.TUE;
        switch (test) {
            case MON:
                System.out.println("今天是星期一");
                break;
            case TUE:
                System.out.println("今天是星期二");
                break;
            // ... ...
            default:
                System.out.println(test);
                break;
        }
    }
}