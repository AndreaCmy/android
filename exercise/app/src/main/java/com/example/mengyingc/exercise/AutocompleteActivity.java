package com.example.mengyingc.exercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class AutocompleteActivity extends AppCompatActivity {

    private static final String TAG = "Autocomplete";
    private AutoCompleteTextView autocomplete;
    String[] datas = new String[]{
            "Chine","Japan","Korean","Russian","USA","Hong Kong"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
Log.i(TAG, "autocomplete");
        autocomplete = (AutoCompleteTextView)findViewById(R.id.autocomp);
        //创建一个ArrayAdapter 封装数组
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                datas);
        //设置Adapter
        autocomplete.setAdapter(aa);
        Log.i(TAG, "end");


    }
}
