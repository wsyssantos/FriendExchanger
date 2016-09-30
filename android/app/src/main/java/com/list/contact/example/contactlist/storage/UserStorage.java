package com.list.contact.example.contactlist.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wesley on 9/3/16.
 */
public class UserStorage {
    private static final String USER_TOKEN_KEY = "user_token";
    private static final String USER_STORAGE_PREFS = "user_prefs";
    private static UserStorage _userStorage;

    public UserStorage() {
    }

    public void setToken(Context context, String token) {
        SharedPreferences preferences = context.getSharedPreferences(USER_STORAGE_PREFS, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_TOKEN_KEY, token);
        editor.commit();
    }

    public String getToken(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USER_STORAGE_PREFS, 0);
        String userToken = preferences.getString(USER_TOKEN_KEY, null);
        return userToken;
    }

    public static UserStorage getInstance() {
        if(_userStorage == null) {
            _userStorage = new UserStorage();
        }
        return _userStorage;
    }
}
