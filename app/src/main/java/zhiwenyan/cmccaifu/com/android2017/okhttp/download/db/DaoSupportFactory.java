package zhiwenyan.cmccaifu.com.android2017.okhttp.download.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

/**
 * Created by yanzhiwen on 6/6/17.
 */

public class DaoSupportFactory {

    private SQLiteDatabase mSQLiteDatabase;

    //持有外部数据库的引用  判断是否有内存卡，6.0需要动态申请权限；
    private DaoSupportFactory() {
        File dbRoot = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "nhdz" + File.separator
                + "database");
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }
        File dbFile = new File(dbRoot, "nhdz.db");

        //打开或者创建一个数据库
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);
    }

    public void init(Context context) {
        File dbRoot = new File(context.getCacheDir() + File.separator + "database");
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }
        File dbFile = new File(dbRoot, "nhdz.db");

        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile, null);

    }




    private static class SingleTon {
        private final static DaoSupportFactory INSTANCE = new DaoSupportFactory();
    }

    public <T> IDaoSupport<T> getDao(Class<T> clazz) {
        IDaoSupport<T> daoSupport = new DaoSupport();
        daoSupport.init(mSQLiteDatabase, clazz);
        return daoSupport;
    }

}
