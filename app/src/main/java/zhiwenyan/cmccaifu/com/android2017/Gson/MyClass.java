package zhiwenyan.cmccaifu.com.android2017.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * @SerializedName
 * 表示某个成员变量序列化/反序列化的名字，无需在GsonBuilder中配置就能生效,甚至会覆盖掉FieldNamingPolicy。
 * Data：5/14/2018-5:10 PM
 *
 * @author yanzhiwen
 */
public class MyClass {
    @SerializedName("name") //a => name
            String a;
    @SerializedName(value="name1", alternate={"name2", "name3"})
    String b;

    String c;

    public MyClass(String a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}