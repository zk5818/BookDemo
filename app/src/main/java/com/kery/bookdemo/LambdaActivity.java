package com.kery.bookdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Observable;

public class LambdaActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);


        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(ev -> {

            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        });
    }

    private void threadTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        new Thread(() -> Toast.makeText(this, "", Toast.LENGTH_LONG).show()).start();


        CheckBox checkBox = new CheckBox(this);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

        });
        checkBox.setOnCheckedChangeListener((btv,isChecked)->{


        });


        Observable.from(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 2 == 0;
                    }
                })
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * integer;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });

    }


}
