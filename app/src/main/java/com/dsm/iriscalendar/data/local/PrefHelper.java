package com.dsm.iriscalendar.data.local;

public interface PrefHelper {

    void saveUuid(String uuid);

    void deleteUuid();

    String getUuid();

    void saveToken(String token);

    void deleteToken();

    String getToken();
}
