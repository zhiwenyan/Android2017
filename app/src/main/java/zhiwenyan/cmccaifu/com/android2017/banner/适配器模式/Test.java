package zhiwenyan.cmccaifu.com.android2017.banner.适配器模式;

/**
 * Created by zhiwenyan on 6/1/17.
 */

public class Test {
    /**
 * Target角色
 */
public interface FiveVolt {
    int getVolt5();
}

    /**
     * Adaptee角色,需要被转换的对象
     */
    public class Volt220 {
        public int getVolt220() {
            return 220;
        }
    }

    // 对象适配器模式
    public class ObjectAdapter implements FiveVolt {

        Volt220 mVolt220;

        public ObjectAdapter(Volt220 adaptee) {
            mVolt220 = adaptee;
        }

        public int getVolt220() {
            return mVolt220.getVolt220();
        }

        @Override
        public int getVolt5() {
            return 5;
        }

    }

}
