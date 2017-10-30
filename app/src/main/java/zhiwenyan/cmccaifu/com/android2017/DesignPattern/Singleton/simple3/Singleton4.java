package zhiwenyan.cmccaifu.com.android2017.DesignPattern.Singleton.simple3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanzhiwen on 2017/10/25.
 * <p>
 * <p>
 * 容器管理  系统的服务就是用这种
 */

public class Singleton4 {
    private static Map<String, Object> sSingleMap = new HashMap<>();

    static {
        sSingleMap.put("activity manager", new Singleton4());

    }

    private Singleton4() {
    }

    public static Object getInstance(String serviceName) {
        return sSingleMap.get(serviceName);
    }
}
