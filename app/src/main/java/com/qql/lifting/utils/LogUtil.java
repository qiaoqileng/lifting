package com.qql.lifting.utils;

import android.util.Log;

import com.qql.lifting.BuildConfig;


/**
 * Created by qiao on 2016/11/25.
 */

public class LogUtil {

    private static final boolean isDebug = BuildConfig.DEBUG;

    private static final int stackLevel = 4;

    public static final String TAG = BuildConfig.APPLICATION_ID;

    public static void v(Object... msg) {
        printLog(Log.VERBOSE, msg);
    }

    public static void d(Object... msg) {
        printLog(Log.DEBUG, msg);
    }

    public static void e(Object... msg) {
        printLog(Log.ERROR, msg);
    }

    private static void printLog(int level, Object... msg) {
        if (isDebug) {
            StringBuilder str = new StringBuilder();

            if (msg != null) {
                for (Object obj : msg) {
                    str.append("★").append(obj);
                }
                if (str.length() > 0) {
                    str.deleteCharAt(0);
                }
            } else {
                str.append("null");
            }
            try {
                StackTraceElement[] sts = Thread.currentThread().getStackTrace();
                StackTraceElement st = null;
                String tag = null;
                if (sts != null && sts.length > stackLevel) {
                    st = sts[stackLevel];
                    if (st != null) {
                        String fileName = st.getFileName();
                        tag = (fileName == null) ? "Unkown" : fileName.replace(".java", "");
                        str.insert(0, "【" + tag + "." + st.getMethodName() + "() line " + st.getLineNumber() + "】\n>>>[")
                                .append("]");
                    }
                }

                tag =  (tag==null)?"dagger2":("dagger2_" + tag);

                while (str.length() > 0) {
                    DEFAULT_LOGHANDLER.publish(tag, level, str.substring(0, Math.min(2000, str.length())).toString());
                    str.delete(0, 2000);
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void printStackTrace() {
        if (isDebug) {
            try {
                StackTraceElement[] sts = Thread.currentThread().getStackTrace();
                for (StackTraceElement stackTraceElement : sts) {
                    DEFAULT_LOGHANDLER.publish("Log_StackTrace", Log.ERROR, stackTraceElement.toString());
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void printException(String msg, Throwable e) {
        if (isDebug) {
            DEFAULT_LOGHANDLER.publish("Log_StackTrace", Log.ERROR, msg + '\n' + Log.getStackTraceString(e));
        }
    }

    public static void printException(Throwable e) {
        if (isDebug) {
            DEFAULT_LOGHANDLER.publish("Log_StackTrace", Log.ERROR, TAG + '\n' + Log.getStackTraceString(e));
        }
    }

    public static interface LogHandler {

        void publish(String tag, int level, String message);

    }

    public static LogHandler DEFAULT_LOGHANDLER = new LogHandler() {
        @Override
        public void publish(String tag, int level, String message) {
            Log.println(level, tag, message);
        }
    };

    public static void setLogHandler(LogHandler logHandler) {
        DEFAULT_LOGHANDLER = logHandler;
    }
}
