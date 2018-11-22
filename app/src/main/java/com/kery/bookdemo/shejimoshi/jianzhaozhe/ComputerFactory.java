package com.kery.bookdemo.shejimoshi.jianzhaozhe;

import com.kery.bookdemo.shejimoshi.gongchang.Computer;
import com.kery.bookdemo.shejimoshi.gongchang.HpComputer;
import com.kery.bookdemo.shejimoshi.gongchang.LenovoComputer;

/**
 * Created by Administrator on 2018/11/5.
 */

public class ComputerFactory {
    public static Computer computerCreate(String s) {
        Computer computer = null;
        switch (s) {
            case "lx":
                computer = new LenovoComputer();
                break;
            case "hp":
                computer = new HpComputer();
                break;
        }
        return computer;
    }
}
