package com.github.tenx.xcom.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import com.github.tenx.xcom.di.scopes.ApplicationContext;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper{


    private SharedPreferences mPrefs;
    private static final String PREF_KEY_ACCESS_TOKEN = "pref_key_access_token";
    private static final String EMAIL_ACCESS_KEY = "Myemail";
    private static final String USER_TYPE_KEY = "UserType";
    public static final String USER_ID = "userId";

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context, @PreferencesInfo String filename){
        mPrefs = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
    }


    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, "");
    }

    //TODO test this
    @Override
    public void setAccessToken(String token) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, token).commit();
    }

    @Override
    public void setEmail(String email) {

        mPrefs.edit().putString(EMAIL_ACCESS_KEY, email).apply();
    }

    @Override
    public String getEmail() {
        return mPrefs.getString(EMAIL_ACCESS_KEY, "");
    }



    @Override
    public void setUserId(String userId) {
        mPrefs.edit().putString(USER_ID,userId).apply();
    }

    @Override
    public String getUserId() {
        return mPrefs.getString(USER_ID,"");
    }


}
