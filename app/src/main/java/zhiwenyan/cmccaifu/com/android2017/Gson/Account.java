package zhiwenyan.cmccaifu.com.android2017.Gson;

import com.google.gson.annotations.Expose;

/**
 * Description: @Expose注解 该注解能够指定该字段是否能够序列化或者反序列化，默认的是都支持（true）。
 * Data：7/25/2018-10:51 AM
 *
 * @author yanzhiwen
 */
public class Account {

    @Expose(deserialize = false)
    private String accountNumber;

    @Expose
    private String iban;

    @Expose(serialize = false)
    private String owner;

    @Expose(serialize = false, deserialize = false)
    private String address;

    private String pin;
}
