package com.finalzhou.myimageview15;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button selectImageBtn;
    private Button cutImageBtn;
    private ImageView imageView;
    //声明两个静态的整型变量，主要是用于意图的返回的标志
    private static final int IMAGE_SELECT = 1;//选择图片
    private static final int IMAGE_CAT = 2;//裁剪图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectImageBtn = (Button) findViewById(R.id.selectImagebtn);
        cutImageBtn = (Button) findViewById(R.id.cutImagebtn);
        imageView = (ImageView) findViewById(R.id.imageview);
        selectImageBtn.setOnClickListener(this);
        cutImageBtn.setOnClickListener(this);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            //处理图片按照手机的屏幕大小显示
            if (requestCode == IMAGE_SELECT) {
                Uri uri = data.getData();//获得图片的路径
                int dw = getWindowManager().getDefaultDisplay().getWidth();//获得屏幕的宽度
                int dh = getWindowManager().getDefaultDisplay().getHeight() / 2;
                try {
                    //实现对图片的裁剪的类，是一个匿名的内部类
                    BitmapFactory.Options factory = new BitmapFactory.Options();
                    factory.inJustDecodeBounds = true;//如果设置为true，允许查询图片不是按照像素分配给内存
                    Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, factory);
                    //对图片的宽度和高度对应手机的屏幕进行匹配
                    //如果大于1，表示图片的高度大于手机屏幕的高度
                    int hRatio = (int) Math.ceil(factory.outHeight / (float) dh);//向下取整的功能
                    //如果大于1，表示图片的宽度大于手机屏幕的宽度
                    int wRatio = (int) Math.ceil(factory.outWidth / (float) dw);
                    //缩放到1/radio的尺寸和1/radio^2像素
                    if(hRatio>1 || wRatio>1){
                        if(hRatio>wRatio){
                            factory.inSampleSize = hRatio;
                        }else{
                            factory.inSampleSize = wRatio;
                        }
                    }
                    //对
                    factory.inJustDecodeBounds = false;
                    //使用BitmapFactory对图片进行适屏的操作
                    bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri),null,factory);
                    imageView.setImageBitmap(bmp);


                } catch (Exception e) {

                }
                //表示裁剪图片
            }else if (requestCode == IMAGE_CAT){
                Bitmap bitmap = data.getParcelableExtra("data");
                imageView.setImageBitmap(bitmap);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectImagebtn:
                //如何提取手机的图片，并且进行选择图片的功能
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//打开手机图片库
                startActivityForResult(intent, IMAGE_SELECT);


                break;
            case R.id.cutImagebtn:
                Intent intent2 = getImageClipIntent();
                startActivityForResult(intent2, IMAGE_CAT);
                break;
            default:
                break;
        }

    }

    public Intent getImageClipIntent() {
        Intent imageClipIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
        //实现对图片的裁剪功能，必须要设置图片的属性和大小
        imageClipIntent.setType("image/*");//获取任意的图片类型
        imageClipIntent.putExtra("crop", "true");//滑动选中图片区域
        imageClipIntent.putExtra("aspectX", 1);//表示剪切框的比例 ，这里是1:1的效果
        imageClipIntent.putExtra("aspectY", 1);//
        imageClipIntent.putExtra("outputX", 80);//指定输出图片的大小
        imageClipIntent.putExtra("outputY", 80);//
        imageClipIntent.putExtra("return-data", true);//表示有返回值
        return imageClipIntent;
    }
}
