package com.qql.lifting.interfaces;

import android.app.Activity;
import android.support.v4.app.Fragment;

public interface IOpenLoginClient {
    void login(Activity activity);
    void login(Fragment fragment);
    void logout();
    void getInfo();
}
