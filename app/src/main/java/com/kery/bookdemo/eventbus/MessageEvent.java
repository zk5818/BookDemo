package com.kery.bookdemo.eventbus;

import android.graphics.Path;

/**
 * Created by Administrator on 2018/11/6.
 */

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
