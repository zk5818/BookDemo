package com.kery.bookdemo.shejimoshi.gongchangfangfa;

import com.kery.bookdemo.shejimoshi.gongchang.Computer;

/**
 * Created by Administrator on 2018/11/5.
 */

public abstract class ComputerFactory2 {
    public abstract <T extends Computer> T createCom(Class<T> clz);
}
