package com.finalzhou.mycheckbox;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private List<CheckBox> checkBoxes = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

    /*
    * 使用动态加载布局的方法
    * */
        String[] checkboxText = new String[]{"您是学生吗","是否喜欢Android","您喜欢旅游吗","打算出国吗"};
        LinearLayout linearLayout = (LinearLayout)getLayoutInflater().inflate(R.layout.activity_main,null);
        //给指定的checkbox赋值
        for (int i = 0;i<checkboxText.length;i++) {
            //先获得checkbox.xml的对象
            CheckBox checkbox =(CheckBox)getLayoutInflater().inflate(R.layout.checkbox,null);
            checkBoxes.add(checkbox);
            checkBoxes.get(i).setText(checkboxText[i]);
            //
            linearLayout.addView(checkbox,i);
        }
        setContentView(linearLayout);
        Button button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String s = "";
        for(CheckBox checkBox:checkBoxes){
            if (checkBox.isChecked()){
                s+=checkBox.getText()+"\n";
            }
        }
        if ("".equals(s)){
            s = "您没有选中选项";
        }
        //使用一个提示框提示用户的信息
        new AlertDialog.Builder(this).setMessage(s).setPositiveButton("关闭",null).show();
    }


}
