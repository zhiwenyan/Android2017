package zhiwenyan.cmccaifu.com.android2017.retrofit.Retrofit.simple;

/**
 * Description:
 * Data：1/19/2018-4:21 PM
 *
 * @author: yanzhiwen
 */
public class UserLoginResult {

    /**
     * code : 0000
     * data : {"userName":"Steven","userSex":"man"}
     * msg : success
     */

    private String code;
    private String data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
