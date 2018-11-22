package com.kery.bookdemo.shejimoshi.guanchazhe;

/**
 * Created by Administrator on 2018/11/5.
 */

public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notify(String msg);
}
