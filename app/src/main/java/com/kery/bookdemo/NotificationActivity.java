package com.kery.bookdemo;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean enabled = isNotificationEnabled(v.getContext());
                if (!enabled) {

                    Intent localIntent = new Intent();
                    localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= 9) {
                        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        localIntent.setData(Uri.fromParts("package", NotificationActivity.this.getPackageName(), null));
                    } else if (Build.VERSION.SDK_INT <= 8) {
                        localIntent.setAction(Intent.ACTION_VIEW);

                        localIntent.setClassName("com.android.settings",
                                "com.android.settings.InstalledAppDetails");

                        localIntent.putExtra("com.android.settings.ApplicationPkgName",
                                NotificationActivity.this.getPackageName());
                    }
                    startActivity(localIntent);


                } else {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sina.com"));

                    PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                    // 未下拉的样式 R.layout.collapsed
                    RemoteViews collapsed = new RemoteViews(getPackageName(), R.layout.layout_col_notification);
                    collapsed.setTextViewText(R.id.tv2, "关闭状态");

                    //下拉后的样式R.layout.show
                    RemoteViews show = new RemoteViews(getPackageName(), R.layout.layout_show_notification);
                    show.setTextViewText(R.id.tv2, "下拉状态");

                    Notification notify = builder.setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher_round)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                            .setContentIntent(pi)
                            .setContentText("新浪微博")
                            .setCustomContentView(collapsed)//下拉前
                            .setCustomBigContentView(show)//下拉后
                            .setFullScreenIntent(pi, true)
                            .setDefaults(NotificationCompat.PRIORITY_DEFAULT)
                            .build();

                    final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    manager.notify(0, notify);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                manager.cancel(0);//清除通知
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }).start();

//                    Intent mIt = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
//                    PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, mIt, 0);
//                    RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.layout_show_notification);
//                    NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this)
//                            .setSmallIcon(R.mipmap.ic_launcher)
//                            .setContentTitle("My notification")
//                            .setContentText("Hello World!")
//                            .setAutoCancel(true)//点击完自动消失
////                            .setCustomBigContentView(remoteViews);
//                            .setContentIntent(pendingIntent);
//                    Notification notification=builder.build();
//                    notification.bigContentView=remoteViews;
//
////                    builder.build().setLatestEventInfo(this, "通知", "开会啦",pendingIntent);//即将跳转页面，还没跳转
//                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                    manager.notify(1001, notification);
                }


            }
        });
    }

    /**
     * 获取通知权限
     *
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private boolean isNotificationEnabled(Context context) {

        String CHECK_OP_NO_THROW = "checkOpNoThrow";
        String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);

            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
