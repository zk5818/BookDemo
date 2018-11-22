package com.kery.bookdemo.shejimoshi.guanchazhe;

import android.util.Log;

/**
 * Created by Administrator on 2018/11/5.
 */

public class WeiXinUser implements Observer {
    private String name;

    public WeiXinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String msg) {
        Log.e("WeiXinUser", name + "--" + msg);
    }
}
