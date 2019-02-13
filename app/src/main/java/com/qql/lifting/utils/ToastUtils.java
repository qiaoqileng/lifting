//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qql.lifting.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.widget.TextView;
import android.widget.Toast;

import com.qql.lifting.App;

public class ToastUtils {
    private static Toast sToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static boolean isJumpWhenMore;

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(boolean isJumpWhenMore) {
        isJumpWhenMore = isJumpWhenMore;
    }

    public static void showShortToastSafe(final CharSequence text) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(text, 0);
            }
        });
    }

    public static void showShortToastSafe(@StringRes final int resId) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(resId, 0);
            }
        });
    }

    public static void showShortToastSafe(@StringRes final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(resId, 0, args);
            }
        });
    }

    public static void showShortToastSafe(final String format, final Object... args) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(format, 0, args);
            }
        });
    }

    public static void showLongToastSafe(final CharSequence text) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(text, 1);
            }
        });
    }

    public static void showLongToastSafe(@StringRes final int resId) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(resId, 1);
            }
        });
    }

    public static void showLongToastSafe(@StringRes final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(resId, 1, args);
            }
        });
    }

    public static void showLongToastSafe(final String format, final Object... args) {
        sHandler.post(new Runnable() {
            public void run() {
                ToastUtils.showToast(format, 1, args);
            }
        });
    }

    public static void showShortToast(CharSequence text) {
        showToast(text, 0);
    }

    public static void showShortToast(@StringRes int resId) {
        showToast(resId, 0);
    }

    public static void showShortToast(@StringRes int resId, Object... args) {
        showToast(resId, 0, args);
    }

    public static void showShortToast(String format, Object... args) {
        showToast(format, 0, args);
    }

    public static void showLongToast(CharSequence text) {
        showToast(text, 1);
    }

    public static void showLongToast(@StringRes int resId) {
        showToast(resId, 1);
    }

    public static void showLongToast(@StringRes int resId, Object... args) {
        showToast(resId, 1, args);
    }

    public static void showLongToast(String format, Object... args) {
        showToast(format, 1, args);
    }

    private static void showToast(@StringRes int resId, int duration) {
        showToast(getCurrContext().getResources().getText(resId).toString(), duration);
    }

    private static void showToast(@StringRes int resId, int duration, Object... args) {
        showToast(String.format(getCurrContext().getResources().getString(resId), args), duration);
    }

    public static Context getCurrContext() {
        return App.getApplication();
    }

    private static void showToast(String format, int duration, Object... args) {
        showToast(String.format(format, args), duration);
    }

    public static boolean isInMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static void showToast(CharSequence text, int duration) {
        try {
            if (isJumpWhenMore) {
                cancelToast();
            }

            if (sToast == null) {
                sToast = Toast.makeText(getCurrContext(), text, duration);
                TextView tv = new TextView(getCurrContext());
                tv.setTextSize(18.0F);
                sToast.setGravity(17, 0, 0);
            } else {
                sToast.setText(text);
                sToast.setDuration(duration);
            }

            sToast.show();
        } catch (Throwable var3) {
            var3.printStackTrace();
        }

    }

    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }

    }
}
