package com.example.mengyingc.exercise;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {
//该程序模拟填充长度为100的数组
    private int[] data = new int[100];
    int hasData = 0;
    //记录progressbar的完成进度
    int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        final ProgressBar bar = (ProgressBar)findViewById(R.id.bar);
        //创建一个负责更新的进度Handler
        final Handler mHandle = new Handler(){
            @Override
            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
                if(msg.what == 0x11){
                    bar.setProgress(mProgressStatus);
                }
            }
        };

        //启动线程来执行任务
        new Thread(){
            @Override
            public void run() {
//                super.run();
                while(mProgressStatus < 100){
                    //获取耗时操作的完成百分比
                    mProgressStatus = doWork();
                    //发送消息到Handler
                    Message m = new Message();
                    m.what = 0x11;
                    mHandle.sendMessage(m);
                }

            }
        }.start();
    }

    //模拟一个耗时操作
    public int doWork(){
        data[hasData++] = (int)(Math.random()*100);
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return hasData;
    }
}
