package zhiwenyan.cmccaifu.com.android2017.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yanzhiwen on 2017/10/27.
 */
@Target(ElementType.FIELD) //Target代表放在哪里使用 FIELD属性  METHOD 方法 TYPE类
@Retention(RetentionPolicy.RUNTIME) //什么时候起作用，程序运行起作用 RUNTIME运行时 CLASS编译时 SOURCE编程阶段
//上面必须有两个标识
public @interface ViewById {//@interface代表注解

    int value();
}
