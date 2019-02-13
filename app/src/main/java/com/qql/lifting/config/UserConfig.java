package com.qql.lifting.config;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.qql.lifting.App;
import com.qql.lifting.mvp.module.entity.User;
import com.qql.lifting.utils.PreferencesUtils;

public class UserConfig {
    private static final String APP_SHARED_PREFERENCES = "app_user_shared_preferences";
    // 最后的用户信息
    private static final String KEY_LAST_USER_INFO = "key_last_user_info";
    private static User user = new User();
    public static boolean isLogin(){
        return user!= null && user.getId() != 0 && !TextUtils.isEmpty(user.getSid());
    }

    public static void saveUser(User thisUser){
        if (thisUser != null) {
            user = thisUser;
            PreferencesUtils.putString(App.getApplication(),
                    APP_SHARED_PREFERENCES,
                    KEY_LAST_USER_INFO,
                    user.toString());
            enablePush();
        }
    }

    private static void enablePush() {
        // TODO: 2019/1/30 登陆后的操作
    }

    public static void initUserLoginStatus() {
        String userInfoStr = PreferencesUtils.getString(
                App.getApplication(),
                APP_SHARED_PREFERENCES,
                KEY_LAST_USER_INFO,
                new User().toString());
        User userInfo = new Gson().fromJson(userInfoStr, User.class);
        saveUser(userInfo);
        if (userInfo != null && userInfo.getSid() != null) {
            enablePush();
        }
    }

    /*
     * 退出登录
     * */
    public static void loginOut() {
//        try {
//            PushAgent.getInstance(App.getApplication()).deleteAlias(mCurrentUserInfo.sid, Constants.UMENG_ALIAS_TYPE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        PreferencesUtils.putString(App.getApplication(),
                APP_SHARED_PREFERENCES,
                KEY_LAST_USER_INFO,
                new User().toString());

        user = new User();
        // TODO: 2019/1/30 更新界面
    }
}
