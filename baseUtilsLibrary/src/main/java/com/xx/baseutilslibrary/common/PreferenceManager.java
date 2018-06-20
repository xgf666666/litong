package com.xx.baseutilslibrary.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * PreferenceManager
 * (๑• . •๑)
 * 类描述: SharedPreferences管理类
 * Created by 雷小星🍀 on 2017/6/20 13:41
 */

public class PreferenceManager {
    /**
     * 默认SharedPreferences存储文件名称
     */
    private static final String DefaultSharedPreferencesName = "default";

    /**
     * 获取SharedPreferences
     *
     * @param context 上下文对象
     * @param spName  SharedPreferences存储文件名
     * @return SharedPreferences
     */
    public static SharedPreferences getSharedPreferences(Context context, String spName) {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * 获取默认SharedPreferences存储文件
     *
     * @param context 上下文对象
     * @return SharedPreferences
     */
    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return getSharedPreferences(context, DefaultSharedPreferencesName);
    }

    /**
     * 存入数据
     *
     * @param sharedPreferences SharedPreferences对象
     * @param key               键
     * @param value             值
     */
    public static void putValue(SharedPreferences sharedPreferences, String key, Object value) {
        SharedPreferences.Editor editor = sharedPreferences
                .edit();
        if (value instanceof String) {
            editor.putString(key, (String) value).apply();
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value).apply();
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value).apply();
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value).apply();
        }
    }

    public static <T extends Object> Object getValue(SharedPreferences sharedPreferences, String key, T defValue) {
        if (defValue instanceof String) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defValue);
        } else if (defValue instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        }
        return null;
    }

    /**
     * 清空SharedPreferences对象中存储的数据
     *
     * @param sharedPreferences SharedPreferences对象
     */
    public static void clear(SharedPreferences sharedPreferences) {
        sharedPreferences
                .edit()
                .clear()
                .apply();
    }

}
