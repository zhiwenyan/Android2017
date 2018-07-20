package zhiwenyan.cmccaifu.com.android2017.annotation;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

/**
 * 动态注入
 * Created by yanzhiwen on 2017/10/27.
 */

public class ViewUtil {
    public static void inject(Activity activity) {
        //获取所有属性
        Field[] fields = activity.getClass().getDeclaredFields();
        //过滤关于ViewById的属性
        for (Field filed : fields) {
            ViewById viewById = filed.getAnnotation(ViewById.class);
            if (viewById != null) {
                //findViewById
                View view = activity.findViewById(viewById.value());
                //反射注入
                filed.setAccessible(true);
                try {
                    //activity 属性所在类 view属性的值
                    filed.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
