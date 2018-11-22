package com.kery.bookdemo.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kery.bookdemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {

    private TextView tv_message;
    private Button by_message, bt_subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        tv_message = (TextView) findViewById(R.id.tv_message);
        by_message = (Button) findViewById(R.id.by_message);
        bt_subscription = (Button) findViewById(R.id.bt_subscription);
        by_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(EventBusActivity.this);
            }
        });
        bt_subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Bus2Activity.class));
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onMoonEvent(MessageEvent messageEvent) {
        tv_message.setText(messageEvent.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(EventBusActivity.this);
    }
}
