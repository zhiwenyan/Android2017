package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Description:
 * Data：11/20/2017-2:42 PM
 * Author: yanzhiwen
 */
public class RequestBody {
    //表单格式
    private String type = "multipart/form-data";
    //参数，文件
    private Map<String, Object> params = new HashMap<>();

    private String boundary = createBoundary();

    private String createBoundary() {
        return UUID.randomUUID().toString();
    }

    public String getContentType() {
        return type + boundary;
    }

    public long getContentLength() {
        //多少个字节
        return 0;
    }

    public void onWriteBody(OutputStream outputStream) {

    }

    public RequestBody addParam(String key, String value) {
        params.put(key, value);
        return this;
    }

    public RequestBody type(String type) {
        this.type = type;
        return this;
    }

}
