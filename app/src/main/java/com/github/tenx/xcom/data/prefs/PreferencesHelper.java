package com.github.tenx.xcom.data.prefs;

public interface PreferencesHelper {
    String getAccessToken();

    void setAccessToken(String token);

    void setEmail(String email);

    String getEmail();

    String getTypeUser();

    void setTypeUser(String userType);


}
