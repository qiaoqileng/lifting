package com.qql.lifting.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewUtils {

    public static void hideSoftInputFromWindow(View v) {
        if (v == null)
            return;
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && v.getWindowToken() != null)
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0); // 强制隐藏键盘

    }
}
