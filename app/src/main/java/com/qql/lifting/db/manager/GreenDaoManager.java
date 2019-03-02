package com.qql.lifting.db.manager;

import android.database.sqlite.SQLiteDatabase;

import com.qql.lifting.App;
import com.qql.lifting.constant.Constants;
import com.qql.lifting.db.gen.DaoMaster;
import com.qql.lifting.db.gen.DaoSession;


/**
 * Created by qiao on 2016/12/1.
 */
public class GreenDaoManager {
    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase mDb;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    /**
     * 设置greenDao
     */
    public static void initDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(App.getApplication(), Constants.DB_NAME, null);
        mDb = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
    public static SQLiteDatabase getDb() {
        return mDb;
    }
}
