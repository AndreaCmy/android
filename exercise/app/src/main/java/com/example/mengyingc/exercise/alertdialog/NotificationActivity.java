package com.example.mengyingc.exercise.alertdialog;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mengyingc.exercise.R;

public class NotificationActivity extends AppCompatActivity {

    private int notificationID = 100;
    private int numMessages = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Log.i("xxx","aaa");
//之前一直无法成功发送Notification是因为给发送按钮绑定了2个事件，一个发送，一个取消
        Button btn_sendnote  = (Button)findViewById(R.id.btn_sendnote);
        btn_sendnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("xxx","test");
                displayNotification();
            }
        });

        Button cancle = (Button)findViewById(R.id.btn_cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                //发送通知
                notificationManager.cancel(notificationID);
            }
        });

    }


   public void  displayNotification(){
       Log.i("Notification", "start");

           /* Invoking the default notification service */
//       NotificationCompat.Builder  mBuilder =
//               new NotificationCompat.Builder(NotificationActivity.this);
       Notification.Builder mBuilder = new Notification.Builder(NotificationActivity.this);

       mBuilder.setContentTitle("普通通知")               //标题
               .setContentText("启动其他Activity的通知") //内容
                .setSmallIcon(R.mipmap.ic_launcher)
               .setWhen(System.currentTimeMillis());//Notification的发布时间，默认系统时间
       /* Increase notification number every time a new notification arrives */
       mBuilder.setNumber(++numMessages);

//                创建一个启动其他Activity的Intent
       Intent intent = new Intent(NotificationActivity.this, NotifyActivity.class);
//                Intent[] intents = new Intent[]{intent};
//The stack builder object will contain an artificial back stack for the started Activity.
       //This ensures that navigating backward from the Activity leads out of your application to the Home Screen.
       TaskStackBuilder stackBuilder = TaskStackBuilder.create(NotificationActivity.this);
       //Adds the back stack for the Intent(but not the Intent itself)
       stackBuilder.addParentStack(NotifyActivity.class);
       stackBuilder.addNextIntent(intent);
       PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
       mBuilder.setContentIntent(pendingIntent);



       Log.i("Notification", "send");
       //获取系统的NotificationManager服务
       NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
       //发送通知
       notificationManager.notify(notificationID, mBuilder.build());
       Log.i("Notification", "end");
   }

}
