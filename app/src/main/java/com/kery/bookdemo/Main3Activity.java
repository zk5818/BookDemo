package com.kery.bookdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {
    private ImageView iv;


    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            ViewGroup.LayoutParams layoutParams = iv.getLayoutParams();
            layoutParams.width = (int) (iv.getWidth() * 1.5);
            layoutParams.height = (int) (iv.getHeight() * 1.5);
//        设置控件水平居中
            ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - iv.getMeasuredWidth()) / 2, 0, 0, 0);
            iv.setLayoutParams(layoutParams);

            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        iv = (ImageView) findViewById(R.id.iv);
        handler.sendEmptyMessageDelayed(0, 5000);

    }
}
