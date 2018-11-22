package com.kery.bookdemo.shejimoshi.gongchangfangfa;

import com.kery.bookdemo.shejimoshi.gongchang.Computer;

/**
 * Created by Administrator on 2018/11/5.
 */

public class GDComputerFac extends ComputerFactory2 {
    @Override
    public <T extends Computer> T createCom(Class<T> clz) {
        Computer computer = null;
        String classname = clz.getName();
        try {
            //通过反射获取不同厂家的计算机
            computer = (Computer) Class.forName(classname).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}
