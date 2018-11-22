package com.kery.bookdemo.shejimoshi.gcDemo;

/**
 * Created by Administrator on 2018/11/5.
 */

public class FDDeviceFactory extends BaseDeviceFactory {
    @Override
    public <T extends BaseDevice> T createDevice(Class<T> clz) {
        BaseDevice device = null;
        String classname = clz.getName();
        try {
            //通过反射获取不同厂家的计算机
            device = (BaseDevice) Class.forName(classname).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) device;
    }
}
