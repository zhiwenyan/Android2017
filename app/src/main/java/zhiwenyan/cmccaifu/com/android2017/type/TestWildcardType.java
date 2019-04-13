package zhiwenyan.cmccaifu.com.android2017.type;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * Description:
 * Data：3/6/2019-11:07 AM
 *
 * @author yanzhiwen
 */
public class TestWildcardType {
    private List<? extends Number> a;  // // a没有下界, 取下界会抛出ArrayIndexOutOfBoundsException
    private List<? super String> b;

    public static void main(String[] args) throws Exception {
        /*
         * 该接口表示通配符泛型, 比如? extends Number 和 ? super Integer 它有如下方法:
         * Type[] getUpperBounds(): 获取范型变量的上界
         * Type[] getLowerBounds(): 获取范型变量的下界
         */
        Field fieldA = TestWildcardType.class.getDeclaredField("a");
        Field fieldB = TestWildcardType.class.getDeclaredField("b");
        // 先拿到范型类型
        ParameterizedType pTypeA = (ParameterizedType) fieldA.getGenericType();
        ParameterizedType pTypeB = (ParameterizedType) fieldB.getGenericType();
        // 再从范型里拿到通配符类型
        WildcardType wTypeA = (WildcardType) pTypeA.getActualTypeArguments()[0];
        WildcardType wTypeB = (WildcardType) pTypeB.getActualTypeArguments()[0];
        // 方法测试
        System.out.println(wTypeA.getUpperBounds()[0]);   // class java.lang.Number
        System.out.println(wTypeB.getLowerBounds()[0]);   // class java.lang.String
        // 看看通配符类型到底是什么, 打印结果为: ? extends java.lang.Number
        System.out.println(wTypeA);
    }

}
