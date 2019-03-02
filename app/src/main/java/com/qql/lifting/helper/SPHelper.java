package com.qql.lifting.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SPHelper {
    private static SPHelper instance;
    private SharedPreferences sp;
    private Context mContext;

    private SPHelper(Context context) {
        this.mContext = context;
        this.sp = this.mContext.getSharedPreferences("tq_tinker_patch", 0);
    }

    public static SPHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SPHelper(context);
        }

        return instance;
    }

    public String get(String key, String defValue) {
        return this.sp.getString(key, defValue);
    }

    public boolean get(String key, boolean defValue) {
        return this.sp.getBoolean(key, defValue);
    }

    public float get(String key, float defValue) {
        return this.sp.getFloat(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return this.sp.getInt(key, defValue);
    }

    public long get(String key, long defValue) {
        return this.sp.getLong(key, defValue);
    }

    public void put(String key, String value) {
        if (value == null) {
            this.sp.edit().remove(key).commit();
        } else {
            this.sp.edit().putString(key, value).commit();
        }

    }

    public void put(String key, boolean value) {
        this.sp.edit().putBoolean(key, value).commit();
    }

    public void put(String key, float value) {
        this.sp.edit().putFloat(key, value).commit();
    }

    public void put(String key, long value) {
        this.sp.edit().putLong(key, value).commit();
    }

    public void putInt(String key, int value) {
        this.sp.edit().putInt(key, value).commit();
    }

    public void remove(String key) {
        this.sp.edit().remove(key).commit();
    }
}
