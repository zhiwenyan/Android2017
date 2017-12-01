package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import java.io.File;
import java.io.IOException;

/**
 * Description:
 * Data：11/30/2017-9:43 AM
 *
 * @author: yanzhiwen
 */
public interface DownLoadCallBack {
    void onFailure(IOException e);

    void onSuccess(File file);
}
