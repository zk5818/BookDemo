package com.kery.bookdemo.img;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kery.bookdemo.R;

public class PublishStatusActivity extends AppCompatActivity {
    public static final int TYPE_PICTURE = 1;                   // 动态类型为图片
    public static final int TYPE_TEXT = 2;                      // 动态类型为文字
    public static final String KEY_TYPE = "type_key";           // 动态类型
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_status);
    }
}
