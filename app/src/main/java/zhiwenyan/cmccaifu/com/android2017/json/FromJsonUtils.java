package zhiwenyan.cmccaifu.com.android2017.json;

import com.google.gson.GsonBuilder;

/**
 * Description:
 * Data：3/30/2018-9:24 AM
 *
 * @author: yanzhiwen
 */
public class FromJsonUtils {

    public static ResultData fromJson(String json, Class clazz) {
        return new GsonBuilder()
                .registerTypeAdapter(ResultData.class, new JsonFormatParser(clazz))
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create()
                .fromJson(json, ResultData.class);
    }
}