package com.kery.bookdemo.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kery.bookdemo.R;

import org.greenrobot.eventbus.EventBus;

public class Bus2Activity extends AppCompatActivity {

    private TextView tv_message;
    private Button by_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus2);

        tv_message = (TextView) findViewById(R.id.tv_message);
        by_message = (Button) findViewById(R.id.by_message);

        by_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EventBus.getDefault().post(new MessageEvent("发你了啊打  我很不开心"));
                EventBus.getDefault().postSticky(new MessageEvent("粘住你了"));
            }
        });
    }
}
