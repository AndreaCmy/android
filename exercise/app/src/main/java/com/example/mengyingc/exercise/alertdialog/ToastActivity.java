package com.example.mengyingc.exercise.alertdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mengyingc.exercise.R;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);


        Button toastBtn = (Button) findViewById(R.id.btn_toast);
        toastBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(ToastActivity.this,
                        "简单的提示信息",
                        //提示信息的持续时间
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
