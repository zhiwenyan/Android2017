package zhiwenyan.cmccaifu.com.android2017.Gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description: @SerializedName注解 该注解能指定该字段在JSON中对应的字段名称
 * Data：7/25/2018-10:50 AM
 *
 * @author yanzhiwen
 */
public class Box {

    @SerializedName("w")
    private int width;

    @SerializedName("h")
    private int height;

    @SerializedName("d")
    private int depth;

    // 也就是说{"w":10,"h":20,"d":30} 这个JSON 字符串能够被解析到上面的width，height和depth字段中。
}
