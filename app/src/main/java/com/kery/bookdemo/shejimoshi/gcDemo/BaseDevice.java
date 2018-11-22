package com.kery.bookdemo.shejimoshi.gcDemo;

/**
 * Created by Administrator on 2018/11/5.
 */

public abstract class BaseDevice {
    public int id;
    public String deviceName;
    public String deviceCode;

    public abstract void setDeviceCode(String code);
    public abstract String getDeviceCode();
}
