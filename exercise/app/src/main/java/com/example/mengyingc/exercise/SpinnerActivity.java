package com.example.mengyingc.exercise;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SpinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        BaseAdapter ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            //重写该方法，该方法返回的View将作为列表框的每项
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = new TextView(SpinnerActivity.this);
                textView.setText(position + " ");
                textView.setTextSize(20);
//                getResources().getColor();//deprecated
                textView.setTextColor(ContextCompat.getColor(SpinnerActivity.this,R.color.colorAccent));
                return textView;
            }
        };
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(ba);
    }



}
