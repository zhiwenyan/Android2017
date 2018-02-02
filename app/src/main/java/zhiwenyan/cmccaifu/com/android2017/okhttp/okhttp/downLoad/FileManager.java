package zhiwenyan.cmccaifu.com.android2017.okhttp.okhttp.downLoad;

import android.content.Context;

import java.io.File;


/**
 * Description:
 * Data：11/30/2017-10:27 AM
 *
 * @author: yanzhiwen
 */
public class FileManager {
    private File mRootDir;
    private static final FileManager sFileManager = new FileManager();
    private Context mContext;

    private FileManager() {
    }

    public static FileManager getFileManager() {
        return sFileManager;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();

    }

    public void rootDir(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file.exists() && file.isDirectory()) {
            mRootDir = file;
        }
    }

    /**
     * 通过网络的路径获取一个本地的文件路径
     *
     * @return
     */
    public File getFile(String url) {
        String fileName = Utils.md5Url(url);
        if (mRootDir == null) {
            mRootDir = mContext.getCacheDir();
        }
        File file = new File(mRootDir, fileName);
        return file;
    }
}
