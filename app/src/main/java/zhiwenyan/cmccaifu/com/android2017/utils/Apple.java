package zhiwenyan.cmccaifu.com.android2017.utils;

import java.util.HashMap;

/**
 * Description:
 * Data：8/9/2018-10:31 AM
 *
 * @author yanzhiwen
 */
public class Apple {
    private String color;

    public Apple(String color) {
        this.color = color;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Apple))
            return false;
        if (obj == this)
            return true;
        return this.color == (( Apple ) obj).color;
    }

    @Override
    public int hashCode() {
        return this.color.length();
    }

    public static void main(String[] args) {
        Apple a1 = new Apple("green");
        Apple a2 = new Apple("red");

        //hashMap stores apple type and its quantity
        HashMap m = new HashMap();
        m.put(a1, 10);
        m.put(a2, 20);
        System.out.println(m.get(new Apple("green")));
    }
}
