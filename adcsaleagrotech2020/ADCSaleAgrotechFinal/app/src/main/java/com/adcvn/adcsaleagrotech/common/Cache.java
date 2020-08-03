package com.adcvn.adcsaleagrotech.common;

import android.content.Context;
import android.content.SharedPreferences;

public class Cache {

    // lưu cache dữ liệu vào thiết bị
    public static void savePreferenceData(Context context, String prefName,
                                             String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                prefName, 0);
        sharedPreferences.edit().putString(key, value).commit();
    }

    // lấy dữ liệu cache từ thiết bị
    public static String loadPreferenceData(Context context, String prefName,
                                               String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                prefName, 0);
        return sharedPreferences.getString(key, "");
    }

    // kiểm tra key tồn tại trong cache của thiết bị
    public static boolean hasPreferenceData(Context context, String prefName,
                                               String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                prefName, 0);
        return sharedPreferences.contains(key);
    }

    // xóa key tồn tại trong cache của thiết bị
    public static void removePreferenceData(Context context, String prefName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                prefName, 0);
        sharedPreferences.edit().remove(key).commit();
    }

}
