package com.example.mengyingc.exercise.imageswitcher;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.example.mengyingc.exercise.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImageSwitcherActivity extends AppCompatActivity {

    private static final String TAG = "ImageSwitcherActivity";
    //图片太多，out of memory
    // java.lang.OutOfMemoryError: Failed to allocate a 5518092 byte allocation with 4194304 free bytes and 4MB until OOM
    int[] imagesIds = new int[]{
            R.mipmap.beautiful_001,R.mipmap.beautiful_002,R.mipmap.beautiful_003,R.mipmap.beautiful_004,
            R.mipmap.beautiful_005,R.mipmap.beautiful_006,R.mipmap.beautiful_007,R.mipmap.beautiful_008
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_switcher);
        Log.i(TAG, "start");
        //创建一个List集合 ，元素是Map
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=0; i<imagesIds.length; i++){
            Map<String, Object> item = new HashMap<>();
            item.put("image", imagesIds[i]);
            list.add(item);
        }
        //获取显示图片的Switcher
        final ImageSwitcher switcher = (ImageSwitcher)findViewById(R.id.switcher);
        //设置图片更换的动画效果
        switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        //为ImageSwitcher设置图片切换的动画效果
        //所有的类引用需要统一， ViewFactory和LayoutParams都要是ImageSwitcher
        switcher.setFactory(new ImageSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(ImageSwitcherActivity.this);
                imageView.setBackgroundColor(0xff0000);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//设置缩放方式
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ImageSwitcher.LayoutParams.WRAP_CONTENT, ImageSwitcher.LayoutParams.WRAP_CONTENT ));
                return imageView;
            }
        });

        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this
                ,list
                //gridList每一个单元格的样式
                ,R.layout.cell
        , new String[]{"image"}
        , new int[]{R.id.image1}); //cell中的imageView
        Log.i(TAG, "simpleAdapter");
        GridView gridView = (GridView)findViewById(R.id.grid01);
        //为GridView设置Adapter
        gridView.setAdapter(simpleAdapter);
        //添加列表项被选中的监听器
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemSelected");
                //显示当前被选中的图片---setImageResource耗内存
//                switcher.setImageResource(bitmap);
                Bitmap bitmap = readBitMap(ImageSwitcherActivity.this, imagesIds[position % imagesIds.length]);
                Drawable drawable =new BitmapDrawable(ImageSwitcherActivity.this.getResources(), bitmap);
                switcher.setImageDrawable(drawable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //添加列表项被单击的监听器
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick");
                //显示被单击的图片
//                switcher.setImageResource(imagesIds[position % imagesIds.length]);
                Bitmap bitmap = readBitMap(ImageSwitcherActivity.this, imagesIds[position % imagesIds.length]);
                Drawable drawable =new BitmapDrawable(ImageSwitcherActivity.this.getResources(), bitmap);
                switcher.setImageDrawable(drawable);
            }
        });
    }


    /**

     * 以最省内存的方式读取本地资源的图片

     * @param context

     * @param resId

     * @return

     */

    public static Bitmap readBitMap(Context context, int resId){

        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        opt.inPurgeable = true;

        opt.inInputShareable = true;

        // 获取资源图片

        InputStream is = context.getResources().openRawResource(resId);

        return BitmapFactory.decodeStream(is, null, opt);

    }
}
