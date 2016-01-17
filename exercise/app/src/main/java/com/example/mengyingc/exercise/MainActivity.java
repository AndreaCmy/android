package com.example.mengyingc.exercise;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mengyingc.exercise.alertdialog.NotificationActivity;
import com.example.mengyingc.exercise.menu.MenuActivity;


public class MainActivity extends AppCompatActivity {
    public static String TIMING_ACTION = "TIMING_ACTION"; //广播的名字
    private static final String LOG_TAG = "MainActivity";
    private Intent intent = null;
    private Button nextButton;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         nextButton = (Button)findViewById(R.id.next);
            nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Class claz = MenuActivity.class;
                //匿名内部类中使用MainActivity.this 在外边直接使用this
                 intent = new Intent(MainActivity.this, claz);
                startActivity(intent);

            }
        });

    }

}
