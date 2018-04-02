package zhiwenyan.cmccaifu.com.android2017.json;

/**
 * Description:
 * Data：3/30/2018-9:23 AM
 *
 * @author: yanzhiwen
 */
public class ResultData<T> {

    public T data;
    public int code;
    public String message;
    /**
     * 扩展字段
     * 0:data为对象
     * 1:data为集合
     * 2:date为空或者null字段
     */
    public int dataType;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", dataType=" + dataType +
                '}';
    }
}