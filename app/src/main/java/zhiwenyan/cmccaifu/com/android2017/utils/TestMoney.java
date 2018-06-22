package zhiwenyan.cmccaifu.com.android2017.utils;

/**
 * Description:
 * Data：6/21/2018-5:02 PM
 *
 * @author yanzhiwen
 */
public class TestMoney {
    private int money = 1000000;
    private double yearRate = 3.6 / 100;
    private double monthRate = 0.3 / 100;
    private int perYearMoney;
    private int perMonthMoney;

    private void year() {
        for (int i = 0; i < 20; i++) {
            perYearMoney = ( int ) (money * yearRate);
            money = money - perYearMoney;
            System.out.println("第" + (i + 1) + "年还：" + perYearMoney);
        }

        System.out.println("money=" + money); //480337
    }

    private void mouth() {
        System.out.println("********************");
        for (int i = 0; i < 12 * 20; i++) {
            perMonthMoney = ( int ) (money * monthRate);
            money = money - perMonthMoney;
            System.out.println("第" + (i + 1) + "个月还：" + perMonthMoney);
        }

        System.out.println("money=" + money); //486309

    }

    public static void main(String[] args) {
        TestMoney testMoney = new TestMoney();
        testMoney.year();
        //testMoney.mouth();
    }
}
