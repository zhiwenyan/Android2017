package zhiwenyan.cmccaifu.com.android2017.type;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Description:
 * Data：3/6/2019-11:22 AM
 *
 * @author yanzhiwen
 */
public class TestGenericArrayType {
    public static void main(String[] args) throws Exception {
        /**
         * 范型数组,组成数组的元素中有范型则实现了该接口; 它的组成元素是ParameterizedType或TypeVariable类型,它只有一个方法:
         *
         * Type getGenericComponentType(): 返回数组的组成对象, 即被JVM编译后实际的对象
         */
        Method method = TestGenericArrayType.class.getDeclaredMethods()[0];

        // public void com.test.Test.show(java.util.List[],java.lang.Object[],java.util.List,java.lang.String[],int[])
        System.out.println(method);
        // 这是 Method 中的方法
        Type[] types = method.getGenericParameterTypes();
        System.out.println(types.length);
        for (Type type : types) {
            System.out.println(type);
        }
    }
}

class Test<T> {
    public void show(List<String>[] pTypeArray, T[] vTypeArray, List<String> list, String[] strings, int[] ints) {
    }

}
