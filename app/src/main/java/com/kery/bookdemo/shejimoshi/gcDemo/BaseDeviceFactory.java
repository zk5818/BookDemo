package com.kery.bookdemo.shejimoshi.gcDemo;

/**
 * Created by Administrator on 2018/11/5.
 */

public abstract class BaseDeviceFactory  {
    public abstract <T extends BaseDevice> T createDevice(Class<T> clz);
}
