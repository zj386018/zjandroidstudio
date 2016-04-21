package com.finalzhou.mybutton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.Button;


/**
 * 版权： 凡路实验室安卓手机应用开发部（c）2015
 * 作者： 周健
 * 创建日期：2015/10/13 0:57
 * 版本： 1.0
 * 添加描述：
 * <p/>
 * 修订历史版本：
 */
public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        SpannableString spannableStringLeft = new SpannableString("left");
        Bitmap bitmapleft = BitmapFactory.decodeResource(getResources(), R.mipmap.button1);
        ImageSpan imageSpanLeft = new ImageSpan(bitmapleft, DynamicDrawableSpan.ALIGN_BOTTOM);
        spannableStringLeft.setSpan(imageSpanLeft, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        SpannableString spannableStringRight = new SpannableString("right");
        Bitmap bitmapright = BitmapFactory.decodeResource(getResources(), R.mipmap.button2);
        ImageSpan imageSpanright = new ImageSpan(bitmapright);
        spannableStringRight.setSpan(bitmapright,0,5,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        button.append(spannableStringLeft);
        button.append("我的按钮");
        button.append(spannableStringRight);

    }

}
