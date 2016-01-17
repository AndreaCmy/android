package com.example.mengyingc.exercise.alertdialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mengyingc.exercise.R;

import java.util.Calendar;

//AppCompatActivity -显示日历格式???
public class DatePickerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);


        Button dateBtn = (Button) findViewById(R.id.btn_date);
        dateBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Calendar c = Calendar.getInstance();
                new TimePickerDialog(DatePickerActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                TextView show = (TextView) findViewById(R.id.tvdate);
                                show.setText(hourOfDay + "时" + minute + "分");
                            }
                        }
                        ,c.get(Calendar.HOUR_OF_DAY)
                        ,c.get(Calendar.MINUTE)
                        ,true //true表示采用24小时制
                ).show();
            }
        });
        /*dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(DatePickerActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                TextView show = (TextView) findViewById(R.id.tvdate);
                                show.setText(year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日");
                            }
                        }
                        //设置初始日期
                        , c.get(Calendar.YEAR)
                        , c.get(Calendar.MONTH)
                        , c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });*/

    }
}
