package com.kery.bookdemo.scroll;

import android.os.Handler;
import android.os.Looper;

/**
 * 全局 Handler 工具类
 * Created by ChengPengFei on 2016/11/9 0009.</br>
 */

public final class HandlerUtil extends Handler {

    private static HandlerUtil instance;

    public static HandlerUtil getInstance() {
        if (instance == null)
            synchronized (HandlerUtil.class) {
                if (instance == null)
                    instance = new HandlerUtil();
            }
        return instance;
    }

    /**
     * 在主线程执行
     * @param runnable
     */
    public void runInMainThread(Runnable runnable) {
        post(runnable);
    }

    private HandlerUtil(){
        super(Looper.getMainLooper());
    }
}
