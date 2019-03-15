package zhiwenyan.cmccaifu.com.android2017.type;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Description:
 * Data：3/6/2019-11:16 AM
 *
 * @author yanzhiwen
 */
public class TestTypeVariable<K extends Comparable & Serializable, V> {
    K key;
    V value;
    public static void main(String[] args) throws Exception {

        /**
         *
         *
         * 类型变量, 范型信息在编译时会被转换为一个特定的类型, 而TypeVariable就是用来反映在JVM编译该泛型前的信息.
         * 它的声明是这样的: public interface TypeVariable<D extends GenericDeclaration> extends Type
         * 也就是说它跟GenericDeclaration有一定的联系, 我是这么理解的:
         * TypeVariable是指在GenericDeclaration中声明的<T>、<C extends Collection>这些东西中的那个变量T、C; 它有如下方法:
         *
         * Type[] getBounds(): 获取类型变量的上边界, 若未明确声明上边界则默认为Object
         * D getGenericDeclaration(): 获取声明该类型变量实体
         * String getName(): 获取在源码中定义时的名字
         */
        // 获取字段的类型
        Field fk = TestTypeVariable.class.getDeclaredField("key");
        Field fv = TestTypeVariable.class.getDeclaredField("value");
        TypeVariable keyType = (TypeVariable)fk.getGenericType();
        TypeVariable valueType = (TypeVariable)fv.getGenericType();
        // getName 方法
        System.out.println(keyType.getName());                 // K
        System.out.println(valueType.getName());               // V
        // getGenericDeclaration 方法
        System.out.println(keyType.getGenericDeclaration());   // class com.test.TestTypeVariable
        System.out.println(valueType.getGenericDeclaration()); // class com.test.TestTypeVariable
        // getBounds 方法
        System.out.println("K 的上界:");                        // 有两个
        for (Type type : keyType.getBounds()) {                // interface java.lang.Comparable
            System.out.println(type);                          // interface java.io.Serializable
        }
        System.out.println("V 的上界:");                        // 没明确声明上界的, 默认上界是 Object
        for (Type type : valueType.getBounds()) {              // class java.lang.Object
            System.out.println(type);
        }
    }

}
