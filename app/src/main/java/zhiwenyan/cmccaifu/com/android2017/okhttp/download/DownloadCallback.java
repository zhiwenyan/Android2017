package zhiwenyan.cmccaifu.com.android2017.okhttp.download;

import java.io.File;

/**
 * Description:
 * Data：3/21/2018-1:46 PM
 *
 * @author: yanzhiwen
 */
public interface DownloadCallback {
    /**
     * 下载成功
     *
     * @param file
     */
    void onSuccess(File file);

    /**
     * 下载失败
     *
     * @param e
     */
    void onFailure(Exception e);
}
