package zhiwenyan.cmccaifu.com.android2017.Enum;

/**
 * Description: 给 enum 自定义属性和方法

 * Data：7/19/2018-2:04 PM
 *
 * @author yanzhiwen
 */
public enum EnumTest1 {
    MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6) {
        @Override
        public boolean isRest() {
            return true;
        }
    },
    SUN(0) {
        @Override
        public boolean isRest() {
            return true;
        }
    };

    private int value;

    private EnumTest1(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isRest() {
        return false;
    }
}
