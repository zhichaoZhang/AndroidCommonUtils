package com.zzc.android.utils;

/**
 * 单例帮助类
 *
 * Created by zczhang on 16/1/26.
 */
public abstract class SingletonHelpUtil<T> {
    private volatile T mInstance;

    protected abstract T create();

    public final T get() {
        if(mInstance == null) {
            synchronized (SingletonHelpUtil.class) {
                if(mInstance == null) {
                    mInstance =  create();
                }
            }
        }
        return mInstance;
    }
}
