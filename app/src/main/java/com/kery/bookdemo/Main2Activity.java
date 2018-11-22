package com.kery.bookdemo;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        findViewById(R.id.bt1).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _createView(v.getContext());
                        if (popupWindow != null && !popupWindow.isShowing()) {
                            popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
                            _openPopupWindowAction();
//                            WindowManager.LayoutParams lp = getWindow().getAttributes();
//                            lp.alpha =  0.5f; //0.0-1.0
//                            getWindow().setAttributes(lp);
                        }
                    }
                }
        );


    }

    /**
     * 第一排图 距离屏幕底部的距离
     */
    int top = 0;
    /**
     * 第二排图 距离屏幕底部的距离
     */
    private LinearLayout llTest1, llTest2, llTest3, llTest4, llTest5, llTest6, llTest7, llTest8;
    int bottom = 0;
    float animatorProperty[] = null;
    PopupWindow popupWindow;
    private View rootVew;
    private ImageView ivBtn;
    private RelativeLayout view_popupBackground;
    private void _createView(final Context context) {
        rootVew = LayoutInflater.from(context).inflate(R.layout.popup_menu_parent, null);
        ivBtn = rootVew.findViewById(R.id.pop_iv_img);
        popupWindow = new PopupWindow(rootVew,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //设置为失去焦点 方便监听返回键的监听
        popupWindow.setFocusable(false);


        // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        //popupWindow.setClippingEnabled(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);

        if (animatorProperty == null) {
            top = dip2px(context, 310);
            bottom = dip2px(context, 210);
            animatorProperty = new float[]{380, 60, -30, -20 - 10, 0};
        }
        llTest1 = rootVew.findViewById(R.id.test1);
        llTest2 = rootVew.findViewById(R.id.test2);
        llTest3 = rootVew.findViewById(R.id.test3);
        llTest4 = rootVew.findViewById(R.id.test4);

        view_popupBackground = rootVew.findViewById(R.id.rlbg);
        view_popupBackground.getBackground().setAlpha(200);
    }

    /**
     * 刚打开popupWindow 执行的动画
     */
    private void _openPopupWindowAction() {
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 0f, 135f);
//        objectAnimator.setDuration(200);
//        objectAnimator.start();

        _startAnimation(llTest1, 500, animatorProperty);
        _startAnimation(llTest2, 430, animatorProperty);
        _startAnimation(llTest3, 430, animatorProperty);
        _startAnimation(llTest4, 500, animatorProperty);

    }

    private void _startAnimation(View view, int duration, float[] distance) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
        anim.setDuration(duration);
        anim.start();
    }


    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
