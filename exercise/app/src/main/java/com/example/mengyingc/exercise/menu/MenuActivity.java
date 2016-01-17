package com.example.mengyingc.exercise.menu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mengyingc.exercise.MainActivity;
import com.example.mengyingc.exercise.R;

public class MenuActivity extends AppCompatActivity {


//定义字体大小菜单项的标识
    final int FONT_10 = 0x111;
    final int FONT_12 = 0x112;
    final int FONT_14 = 0x113;
    final int FONT_16 = 0x114;
    //定义普通菜单项的标识
    final int PLAIN_ITEM = 0x11b;
    //定义字体颜色菜单项的标识
    final int FONT_RED = 0x116;
    final int FONT_BLUE = 0x117;
    final int FONT_GREEN = 0x118;

    //定义性别菜单项的标识
    final int MALE = 0x120;
    final int FEMALE = 0x121;
    private EditText edit;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        edit = (EditText)findViewById(R.id.edit);
        text = (TextView)findViewById(R.id.textView);
        //为文本框注册上下文菜单
        registerForContextMenu(text);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu genderMenu = menu.addSubMenu("性别");
        genderMenu.setHeaderTitle("请选择性别");
        genderMenu.add(0, MALE, 0 , "男性");
        genderMenu.add(0, FEMALE, 0 , "女性");
        menu.setGroupCheckable(0, true, true);//单选按钮

        SubMenu fontMenu = menu.addSubMenu("字体大小");
        fontMenu.setIcon(R.mipmap.ic_launcher);
        fontMenu.setHeaderTitle("选择字体大小");
        fontMenu.add(0, FONT_10, 0, "10号字体");
        fontMenu.add(0, FONT_12, 0, "12号字体");
        fontMenu.add(0, FONT_14, 0, "14号字体");
        fontMenu.add(0, FONT_16, 0, "16号字体");

//        menu.add(0, PLAIN_ITEM, 0, "普通菜单项");
        MenuItem plain = menu.add("普通菜单项");
        plain.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast toast = Toast.makeText(MenuActivity.this, "您单击了普通菜单项", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });

        SubMenu colorMenue = menu.addSubMenu("字体颜色");
        colorMenue.setIcon(R.mipmap.ic_launcher);
        colorMenue.setHeaderTitle("选择字体颜色");
        colorMenue.add(0, FONT_RED, 0, "红色");
        colorMenue.add(0, FONT_GREEN, 0, "绿色");
        colorMenue.add(0, FONT_BLUE, 0, "蓝色");

        SubMenu proMenu = menu.addSubMenu("启动程序");
        proMenu.setHeaderTitle("选择要启动的程序");
        //设置菜单项关联Activity
        MenuItem item = proMenu.add("返回到主页");
        item.setIntent(new Intent(this, MainActivity.class));//不work
        return super.onCreateOptionsMenu(menu);
    }

//菜单项 被单击后的回调方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case FONT_10:
                edit.setTextSize(10*2);
                break;
            case FONT_12:
                edit.setTextSize(12*2);
                break;
            case FONT_14:
                edit.setTextSize(14*2);
                break;
            case FONT_16:
                edit.setTextSize(16*2);
                break;
            case FONT_BLUE:
                edit.setTextColor(Color.BLUE);
                break;
            case FONT_RED:
                edit.setTextColor(Color.RED);
                break;
            case FONT_GREEN:
                edit.setTextColor(Color.GREEN);
                break;
//            case PLAIN_ITEM:
//                Toast toast = Toast.makeText(MenuActivity.this, "您单击了普通菜单项", Toast.LENGTH_SHORT);
//                toast.show();
//                break;
            case FEMALE :
                edit.setText("您的性别为：女");
                item.setChecked(true);//必须通过代码来改变勾选状态
                break;
            case MALE :
                edit.setText("您的性别为：男");
                item.setChecked(true);//必须通过代码来改变勾选状态
                break;
        }
        return true;
    }


    //上下文菜单需要重写的方法，source参数代表触发上下文菜单的组件
    @Override
    public void onCreateContextMenu(ContextMenu menu, View source, ContextMenu.ContextMenuInfo menuInfo) {

        menu.add(0, FONT_RED, 0, "红色");
        menu.add(0, FONT_GREEN, 0, "绿色");
        menu.add(0, FONT_BLUE, 0, "蓝色");
        //将三个菜单项设为单选菜单项
        menu.setGroupCheckable(0, true, true);
        //设置上下文菜单的标题，图标
        menu.setHeaderTitle("选择背景色");

//        super.onCreateContextMenu(menu, source, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case FONT_BLUE:
                text.setBackgroundColor(Color.BLUE);
                break;
            case FONT_RED:
                text.setBackgroundColor(Color.RED);
                break;
            case FONT_GREEN:
                text.setBackgroundColor(Color.GREEN);
                break;
        }
        return true;
    }
}
