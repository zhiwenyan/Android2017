package zhiwenyan.cmccaifu.com.android2017.JavaData;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * Data：8/10/2018-2:42 PM
 *
 * @author yanzhiwen
 */
public class HashMapTest {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Steven");
        Set<Map.Entry<Integer, String>> set = map.entrySet();
        while (set.iterator().hasNext()) {
            System.out.println(set.iterator().next().getValue());
        }

    }
}
