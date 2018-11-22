package com.kery.bookdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kery.bookdemo.eventbus.EventBusActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView t1, t2, t3, t4, t5, t6, t7, t8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = (TextView) findViewById(R.id.t1);
        t1.setOnClickListener(this);
        t2 = (TextView) findViewById(R.id.t2);
        t2.setOnClickListener(this);
        t3 = (TextView) findViewById(R.id.t3);
        t3.setOnClickListener(this);
        t4 = (TextView) findViewById(R.id.t4);
        t4.setOnClickListener(this);
        t5 = (TextView) findViewById(R.id.t5);
        t5.setOnClickListener(this);
        t6 = (TextView) findViewById(R.id.t6);
        t6.setOnClickListener(this);
        t7 = (TextView) findViewById(R.id.t7);
        t7.setOnClickListener(this);
        t8 = (TextView) findViewById(R.id.t8);
        t8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t1:
                startActivity(new Intent(v.getContext(), NotificationActivity.class));
                break;
            case R.id.t2:
                ComponentName componetName = new ComponentName(
                        "com.shuidiguanjia.missouririver",//这个是水滴应用程序的包名
                        "com.shuidiguanjia.missouririver.ui.activity.Login_Activity"); //这个参数是要启动的Activity
                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putString("data1", "这是跳转过来需要带的参数");//暂未定需要传数据
//                intent.putExtras(bundle);
                intent.setComponent(componetName);
                startActivity(intent);
                break;
            case R.id.t3:
                startActivity(new Intent(v.getContext(), FactoryActivity.class));
                break;
            case R.id.t4:
                startActivity(new Intent(v.getContext(), EventBusActivity.class));
                break;
            case R.id.t5:
                startActivity(new Intent(v.getContext(), Main2Activity.class));
                break;
            case R.id.t6:
                startActivity(new Intent(v.getContext(), Main3Activity.class));
                break;
            case R.id.t7:
                startActivity(new Intent(v.getContext(), Main4Activity.class));
                break;
            case R.id.t8:
                startActivity(new Intent(v.getContext(), LambdaActivity.class));
                break;
        }
    }
}
