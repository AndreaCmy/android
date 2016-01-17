package com.example.mengyingc.exercise.tabhost;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.mengyingc.exercise.R;

public class TabActivity extends FragmentActivity implements TabHost.OnTabChangeListener{
private FragmentTabHost tabHost;
private static final String TAG = "tabActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Log.i(TAG, "ready");
        tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        tabHost.setup(this, super.getSupportFragmentManager(), R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();

    }


    private void initTab(){
        Log.i(TAG, "initTab");

        String[] tabs = TabDb.getTabsTxt();
        Log.i(TAG, "tabs.length="+tabs.length);
        for(int i=0;i<tabs.length;i++){
            TabHost.TabSpec tabSpec=tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec,TabDb.getFragments()[i],null);
            tabHost.setTag(i);
        }

    }

    private View getTabView(int idx){
        Log.i(TAG, "getTabView=" +idx);
        View view = LayoutInflater.from(this).inflate(R.layout.fragment_tab, null); //fragment_tab 每个tab按钮的UI
        ((TextView)view.findViewById(R.id.tvTab)).setText(TabDb.getTabsTxt()[idx]);//设置tab显示文字

        if(idx==0){
            ((TextView)view.findViewById(R.id.tvTab)).setTextColor(Color.RED); //第一个tab字体设置为红色
            ((ImageView)view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImgLight()[idx]);
        }else{
            ((ImageView)view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImg()[idx]);
        }
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        Log.i(TAG, "onTabChanged");
        updateTab();
    }

    private void updateTab(){
        Log.i(TAG, "updateTab");
        TabWidget tabWidget = tabHost.getTabWidget();
        for(int i=0; i<tabWidget.getChildCount(); i++){
            View view  = tabWidget.getChildAt(i);
            ImageView iv = (ImageView)view.findViewById(R.id.ivImg);
            if(i == tabHost.getCurrentTab()){
                ((TextView)view.findViewById(R.id.tvTab)).setTextColor(Color.RED);//当前tab页红色
                iv.setImageResource(TabDb.getTabsImgLight()[i]);
            }else{
                //getColor deprecated
                ((TextView)view.findViewById(R.id.tvTab)).setTextColor(ContextCompat.getColor(this, R.color.foot_txt_gray));
                iv.setImageResource(TabDb.getTabsImg()[i]);
            }
        }
    }
}
