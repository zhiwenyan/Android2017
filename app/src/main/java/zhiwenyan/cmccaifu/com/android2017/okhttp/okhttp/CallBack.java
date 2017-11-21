package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp;

import java.io.IOException;

/**
 * Description:
 * Data：11/20/2017-2:26 PM
 * Author: yanzhiwen
 */
public interface CallBack {

    void onFailure(Call call, IOException e);

    void onResponse(Call call, Response response) throws IOException;
}
