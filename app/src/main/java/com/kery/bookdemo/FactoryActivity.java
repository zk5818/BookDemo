package com.kery.bookdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.kery.bookdemo.shejimoshi.guanchazhe.SubScriptionSubject;
import com.kery.bookdemo.shejimoshi.guanchazhe.WeiXinUser;

public class FactoryActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
//                ComputerFactory.computerCreate("lx").start();

//                ComputerFactory2 computerFactory2 = new GDComputerFac();
//                LenovoComputer computer1 = computerFactory2.createCom(LenovoComputer.class);
//                computer1.start();

//                BaseDeviceFactory baseDeviceFactory = new FDDeviceFactory();
//                FDWater XXX = baseDeviceFactory.createDevice(FDWater.class);
//                XXX.setDeviceCode("LM1012");
//                Log.e("--->",XXX.getDeviceCode());
                SubScriptionSubject scriptionSubject=new SubScriptionSubject();
                WeiXinUser weiXinUser1 = new WeiXinUser("张三");
                WeiXinUser weiXinUser2 = new WeiXinUser("张读数");
                WeiXinUser weiXinUser3 = new WeiXinUser("张水滴");
                scriptionSubject.attach(weiXinUser1);
                scriptionSubject.attach(weiXinUser2);
                scriptionSubject.attach(weiXinUser3);
                scriptionSubject.notify("老子赢！");
                break;

        }
    }
}
