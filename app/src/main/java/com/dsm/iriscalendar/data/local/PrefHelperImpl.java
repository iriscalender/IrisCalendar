package com.dsm.iriscalendar.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefHelperImpl implements PrefHelper {

    private SharedPreferences pref;

    public PrefHelperImpl(Context context) {
        pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
    }

    private static final String UUID = "UUID_KEY";
    private static final String TOKEN = "TOKEN_KEY";

    @Override
    public void saveUuid(String uuid) {
        pref.edit().putString(UUID, uuid).apply();
    }

    @Override
    public void deleteUuid() {
        pref.edit().remove(UUID).apply();
    }

    @Override
    public String getUuid() {
        return pref.getString(UUID, "");
    }

    @Override
    public void saveToken(String token) {
        pref.edit().putString(TOKEN, token).apply();
    }

    @Override
    public void deleteToken() {
        pref.edit().remove(TOKEN).apply();
    }

    @Override
    public String getToken() {
        return pref.getString(TOKEN, "");
    }
}
