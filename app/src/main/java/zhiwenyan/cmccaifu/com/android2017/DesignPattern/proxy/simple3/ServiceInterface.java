package zhiwenyan.cmccaifu.com.android2017.DesignPattern.proxy.simple3;

import retrofit2.Call;

/**
 * Description:
 * Data：11/29/2017-10:07 AM
 *
 * @author: yanzhiwen
 */
public interface ServiceInterface {
    Call<String> useLogin();
}
