package com.qql.mylib.utils;


import android.app.Activity;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.schedulers.Schedulers;

public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
   public static  <T> FlowableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }

    /**
     * 统一生命周期处理
     * @param <T>
     * @return
     */
    public static  <T> FlowableTransformer<T, T> bindLifeCircle(Activity activity) {
        return observable -> observable.filter((AppendOnlyLinkedArrayList.NonThrowingPredicate<T>) t -> {
            if (activity == null) return true;
            return !activity.isFinishing();
        });
    }


}
