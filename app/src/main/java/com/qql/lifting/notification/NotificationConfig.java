package com.qql.lifting.notification;

import android.content.Context;
import android.graphics.Bitmap;
import java.util.HashMap;

public class NotificationConfig {
    public static final String TYPE_APP = "1";
    public static final String TYPE_ACTIVITY = "2";
    public static final String TYPE_URL = "3";
    public static final String ALARM_RING = "ring";
    public static final String ALARM_SHAKE = "shake";
    public static final String ALARM_BLN = "bln";
    public NotificationType type;
    public String title;
    public String body;
    public int SmallIcon;
    public Bitmap SmallImg;
    public Bitmap LargeIcon;
    public int BigIcon;
    public Context context;
    public String targetType;
    public String targetString;
    public HashMap<String, String> targetParams;
    public String remindType;

    public NotificationConfig() {
    }

    public boolean isReady() {
        return this.type != null && this.context != null && new Integer(this.SmallIcon) != null;
    }

    public static class Builder {
        private NotificationType type;
        private String title;
        private String body;
        private int SmallIcon;
        public Bitmap SmallImg;
        private Bitmap LargeIcon;
        private int BigIcon;
        private Context context;
        private String targetType;
        private String targetString;
        private HashMap<String, String> targetParams;
        public String remindType;

        public Builder() {
        }

        public NotificationConfig.Builder setType(NotificationType type) {
            this.type = type;
            return this;
        }

        public NotificationConfig.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public NotificationConfig.Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public NotificationConfig.Builder setSmallIcon(int smallIcon) {
            this.SmallIcon = smallIcon;
            return this;
        }

        public NotificationConfig.Builder setLargeIcon(Bitmap largeIcon) {
            this.LargeIcon = largeIcon;
            return this;
        }

        public NotificationConfig.Builder setBigIcon(int bigIcon) {
            this.BigIcon = bigIcon;
            return this;
        }

        public NotificationConfig.Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public String getTargetType() {
            return this.targetType;
        }

        public NotificationConfig.Builder setTargetType(String targetType) {
            this.targetType = targetType;
            return this;
        }

        public String getTargetString() {
            return this.targetString;
        }

        public NotificationConfig.Builder setTargetString(String targetString) {
            this.targetString = targetString;
            return this;
        }

        public HashMap<String, String> getTargetParams() {
            return this.targetParams;
        }

        public void setTargetParams(HashMap<String, String> targetParams) {
            this.targetParams = targetParams;
        }

        public Bitmap getSmallImg() {
            return this.SmallImg;
        }

        public void setSmallImg(Bitmap smallImg) {
            this.SmallImg = smallImg;
        }

        public String getRemindType() {
            return this.remindType;
        }

        public void setRemindType(String remindType) {
            this.remindType = remindType;
        }

        void applyConfig(NotificationConfig config) {
            config.type = this.type;
            config.title = this.title;
            config.body = this.body;
            config.SmallIcon = this.SmallIcon;
            config.SmallImg = this.SmallImg;
            config.LargeIcon = this.LargeIcon;
            config.BigIcon = this.BigIcon;
            config.context = this.context;
            config.targetType = this.targetType;
            config.targetString = this.targetString;
            config.targetParams = this.targetParams;
            config.remindType = this.remindType;
        }

        public NotificationConfig build() {
            NotificationConfig config = new NotificationConfig();
            this.applyConfig(config);
            return config;
        }
    }
}
