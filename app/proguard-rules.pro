# 指定代码的压缩级别 0 - 7(指定代码进行迭代优化的次数，在Android里面默认是5，这条指令也只有在可以优化时起作用。)
-optimizationpasses 5
#不进行优化，建议使用此选项，
-dontoptimize
 # 不进行预校验,Android不需要,可加快混淆速度。
-dontpreverify
# 屏蔽警告
-ignorewarnings
# 保护代码中的Annotation不被混淆
-keepattributes *Annotation*
# 避免混淆泛型, 这在JSON实体映射时非常重要
-keepattributes Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable


#
# ----------------------------- 默认保留 -----------------------------
#
#----------------------------------------------------
# 保持哪些类不被混淆
#继承activity,application,service,broadcastReceiver,contentprovider....不进行混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep class android.support.** {*;}

# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**


#表示不混淆任何包含native方法的类的类名以及native方法名，这个和我们刚才验证的结果是一致
-keepclasseswithmembernames class * {
    native <methods>;
}


#这个主要是在layout 中写的onclick方法android:onclick="onClick"，不进行混淆
#表示不混淆Activity中参数是View的方法，因为有这样一种用法，在XML中配置android:onClick=”buttonClick”属性，
#当用户点击该按钮时就会调用Activity中的buttonClick(View view)方法，如果这个方法被混淆的话就找不到了
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context,android.util.AttributeSet);
    public <init>(android.content.Context,android.util.AttributeSet,int);
    public <init>(android.content.Context,android.util.AttributeSet,int,int);
}

-keep public class * extends android.support.** { *; }

-dontwarn android.support.**
-dontwarn com.squareup.**
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn org.apache.**
-dontwarn org.android.**
-dontwarn org.greenrobot.**
-dontwarn de.greenrobot.**
-dontwarn org.hamcrest.**
-dontwarn com.bumptech.**
-dontwarn com.baidu.**
-dontwarn vi.com.gdi.**
-dontwarn com.google.**
-dontwarn com.umeng.**
-dontwarn com.ta.**
-dontwarn com.ut.**
-dontwarn u.aly.**
-dontwarn com.sina.**
-dontwarn com.tencent.**
-dontwarn com.bm.**

-keep class android.support.**
-keep class com.squareup.**{*; }
-keep class okio.**{*; }
-keep class okhttp3.**{*; }
-keep class org.apache.**{*; }
-keep class org.greenrobot.**{*; }
-keep class de.greenrobot.**{*; }
-keep class org.hamcrest.**{*; }
-keep class org.android.**{*; }
-keep class org.android.**{*; }
-keep class com.bumptech.**{*; }
-keep class com.baidu.**{*; }
-keep class vi.com.gdi.**{*; }
-keep class vi.com.gdi.**{*; }
-keep class com.google.**{*; }
-keep class com.umeng.**{*; }
-keep class com.ut.**{*; }
-keep class com.ta.**{*; }
-keep class u.aly.**{*; }


-keep class com.sina.**{*; }
-keep class com.tencent.**{*; }
-keep class com.bm.**{*; }

-keepattributes Signature
-keep class com.qql.lifting.mvp.module.bean.**{*; }
-keep class com.qql.lifting.mvp.module.response.**{*; }

-keep class com.qql.lifting.MyGlideModule
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
#greendao3.2.0,此是针对3.2.0
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
public static void dropTable(org.greenrobot.greendao.database.Database, boolean);
public static void createTable(org.greenrobot.greendao.database.Database, boolean);
}
-keep class **$Properties

#---------------------- butterknife --------------------
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#---------------------- butterknife --------------------
#baseRecyclerViewAdaper
-keep class com.chad.library.adapter.** {
   *;
}
#---------------------- ument0417 --------------------
-keep public class com.qql.lifting.R$*{
     public static final int *;
 }
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#push sdk
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}
#push sdk
#---------Umeng share--------------
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**
-keep public class com.umeng.socialize.* {*;}
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keepattributes Signature

#okhttputils
-dontwarn com.zhy.http.**
-keep class com.zhy.http.**{*;}
-keep class com.qql.lifting.mvp.module.network.xinfang.ResponseMessage$* {*;}

#glide4.0
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

#PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

 #rxjava
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#rxandroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

#baidu Map
-keep class mapsdkvi.com.** {*;}

-dontwarn com.tianque.appcloud.**
-dontwarn com.tianque.messagecenter.**
#-dontwarn io.socket.**
-keep class com.tianque.appcloud.** {*;}
-keep class com.tianque.messagecenter.** {*;}
#-keep class io.socket.** {*;}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }

#-dontskipnonpubliclibraryclassmembers
#-dontskipnonpubliclibraryclasses
-keep class com.alibaba.fastjson.**{*;}
-dontwarn com.alibaba.fastjson.**
#-keep class * implements java.io.Serializable { *; }
#-keepattributes *Annotation
#-keepattributes Signature
 #如果有问题直接使用-libraryjars libs/fastjson-1.1.46.android.jar-dontwarn com.alibaba.fastjson.** -keep class com.alibaba.fastjson.** { *; } -keepattributes Signature

#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions