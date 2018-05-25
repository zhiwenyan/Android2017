package zhiwenyan.cmccaifu.com.android2017.Gson;

import com.google.gson.annotations.Expose;

/**
 * Description:
 * 表示某个成员变量暴露于JSON序列化/反序列化。
 * 只需在GsonBuilder中配置excludeFieldsWithoutExposeAnnotation()时才会生效。当配置过后，只有使用了@Expose注解的成员变量才会参与序列化/反序列化工作。
 * Data：5/14/2018-5:09 PM
 *
 * @author yanzhiwen
 */
public class User {
    @Expose
    private String firstName;//参与序列化(JAVA对象转为JSON)/反序列化（JSON转为JAVA对象）

    @Expose(serialize = false)
    private String lastName;//参与反序列化

    @Expose(serialize = false, deserialize = false)
    private String emailAddress; //不参与

    private String password;//不参与
}
