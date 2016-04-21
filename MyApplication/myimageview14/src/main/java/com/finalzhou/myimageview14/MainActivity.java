package com.finalzhou.myimageview14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageview);
        //设置第一个图片的比例大小
        //表示宽度是200，高度是100
        imageView.setLayoutParams(new LinearLayout.LayoutParams(200,100));
        setTitle("高度:"+imageView.getLayoutParams().height+"宽度："+imageView.getLayoutParams().width);

    }


}
