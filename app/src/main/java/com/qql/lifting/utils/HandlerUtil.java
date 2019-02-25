package com.qql.lifting.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

public final class HandlerUtil {
    public HandlerUtil() {
    }

    public static void post(Runnable paramRunnable) {
        if (paramRunnable != null) {
            HandlerUtil.IHANDLER.HANDLER.post(paramRunnable);
        }
    }

    public static boolean isValid(Activity activity) {
        return activity != null && !activity.isFinishing();
    }

    private interface IHANDLER {
        Handler HANDLER = new Handler(Looper.getMainLooper());
    }
}
