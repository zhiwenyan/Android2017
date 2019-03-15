package zhiwenyan.cmccaifu.com.android2017.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Description:
 * Data：3/6/2019-10:26 AM
 *
 * @author yanzhiwen
 */
public class TestParameterizedType {
    Map<String, String> map;


    public static void main(String[] args) throws Exception {
        /**
         * Type getRawType(): 返回承载该泛型信息的对象, 如上面那个Map<String, String>承载范型信息的对象是Map
         * Type[] getActualTypeArguments(): 返回实际泛型类型列表, 如上面那个Map<String, String>实际范型列表中有两个元素, 都是String
         * Type getOwnerType(): 返回是谁的member.(上面那两个最常用)
         */
        Field f = TestParameterizedType.class.getDeclaredField("map");
        System.out.println(f.getGenericType());                               // java.util.Map<java.lang.String, java.lang.String>
        System.out.println(f.getGenericType() instanceof ParameterizedType);  // true
        ParameterizedType pType = (ParameterizedType) f.getGenericType();
        System.out.println(pType.getRawType());                               // interface java.util.Map
        for (Type type : pType.getActualTypeArguments()) {
            System.out.println(type);                                         // 打印两遍: class java.lang.String
        }
        System.out.println(pType.getOwnerType());                             // null
    }

}
