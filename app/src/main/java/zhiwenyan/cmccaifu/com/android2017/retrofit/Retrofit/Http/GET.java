package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.Http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 * Data：1/19/2018-1:56 PM
 *
 * @author: yanzhiwen
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GET {
    String value();
}
