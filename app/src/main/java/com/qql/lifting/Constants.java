package com.qql.lifting;

import java.io.File;

/**
 * Created by qiao on 2016/11/28.
 */

public class Constants {
    public static final String PKG_NAME = App.getApplication().getPackageName();

    public static final String DB_NAME = PKG_NAME.replace(".", "_");

    public static final String PATH_DATA = App.getApplication().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String USER_ID = "user_id";

    public static final String CACHE_NAME = "lifting";
    public static final int HISTORY_ROWS = 10;
}
