package com.qql.lifting.helper;

import android.text.TextUtils;

import com.qql.lifting.App;
import com.qql.lifting.BuildConfig;
import com.qql.lifting.constant.LoginType;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginClientHelper {
    private static Tencent tencent;
    public static Tencent getQQInstance(){
        if (tencent == null){
            tencent = Tencent.createInstance(BuildConfig.QQ_APPID, App.getApplication());
        }
        return tencent;
    }

    public static void saveQQOpenIdToken(JSONObject jsonObject) throws JSONException {
        String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
        String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
        String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
        if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                && !TextUtils.isEmpty(openId)) {
            SPHelper helper = SPHelper.getInstance(App.getApplication());
            helper.put(LoginType.KEY_TOKEN,token);
            helper.put(LoginType.KEY_OPEN_ID,openId);
            helper.put(LoginType.KEY_EXPIRES_IN,expires);

            getQQInstance().setAccessToken(token, expires);
            getQQInstance().setOpenId(openId);
        }
    }
}
