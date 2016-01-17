package com.example.mengyingc.exercise.imageswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import com.example.mengyingc.exercise.R;

public class GralleryActivity extends AppCompatActivity {
    int[] imageIDs = new int[] { R.mipmap.beautiful_001,
            R.mipmap.beautiful_002, R.mipmap.beautiful_003,
            R.mipmap.beautiful_004, R.mipmap.beautiful_005,
            R.mipmap.beautiful_006, R.mipmap.beautiful_007,
            R.mipmap.beautiful_008, R.mipmap.beautiful_009,
            R.mipmap.beautiful_010, R.mipmap.beautiful_011,
            R.mipmap.beautiful_012, R.mipmap.beautiful_013 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galary);

        //Gallery替代品 HorizontalScrollView
        final Gallery gallery = (Gallery) findViewById(R.id.gallery);
        final ImageSwitcher switcher = (ImageSwitcher) findViewById(R.id.gallerySwitcher);
        // 为ImageSwitch设置图片切换的图片
        switcher.setFactory(new ImageSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(GralleryActivity.this);
                imageView.setBackgroundColor(0xff0000);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.WRAP_CONTENT,
                        ImageSwitcher.LayoutParams.WRAP_CONTENT));
                return imageView;
            }
        });
        // 设置图片切换的动画效果
        switcher.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));
        //创建一个 BaseAdapter对象，该对象负责提供 Gallery所显示的每张图片
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView = new ImageView(GralleryActivity.this);
                imageView
                        .setImageResource(imageIDs[position % imageIDs.length]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(new Gallery.LayoutParams(75,100));
                return imageView;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return imageIDs.length;
            }
        };

        gallery.setAdapter(adapter);

        // 处理被选中事件
        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // 显示当前被选中的图片
                switcher.setImageResource(imageIDs[position % imageIDs.length]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
