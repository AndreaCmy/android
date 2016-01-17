package com.example.mengyingc.exercise.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mengyingc.exercise.R;

public class AlertDialogActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        Button button = (Button) findViewById(R.id.btn_alert);
        //定义一个AlertDialog.Builder对象
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //设置对话框的图标
                builder.setIcon(R.mipmap.foot_fond_normal);
                //设置对话框的标题
                builder.setTitle("自定义普通对话框");
                //设置对话框显示的内容
                builder.setMessage("一个简单的提示对话框");
                //为对话框设置一个”确定“按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView show = (TextView) findViewById(R.id.tvdate);
                        show.setText("单击了“确定”按钮！");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView show = (TextView) findViewById(R.id.tvdate);
                        show.setText("单击了“取消”按钮！");
                    }
                });
                //创建并显示对话框
                builder.create().show();

            }
        });



    }


}
