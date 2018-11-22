package com.kery.bookdemo.shejimoshi.guanchazhe;

import android.text.style.ForegroundColorSpan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/5.
 */

public class SubScriptionSubject implements Subject {
    private List<Observer> weixinUserList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        weixinUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weixinUserList.remove(observer);
    }

    @Override
    public void notify(String msg) {
        for (Observer o:weixinUserList
             ) {
            o.update(msg);
        }
    }
}
