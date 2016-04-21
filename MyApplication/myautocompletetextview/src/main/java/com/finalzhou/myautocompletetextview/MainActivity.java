package com.finalzhou.myautocompletetextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView auto;
    private MultiAutoCompleteTextView mul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auto = (AutoCompleteTextView) findViewById(R.id.autotext);
        String[] autoStrings = new String[]{"行健轩1", "行健轩2", "行健轩3", "行健轩4",
                "至诚轩1", "至诚轩2", "至诚轩3", "至诚轩4"};

//        参数 android.R.layout.simple_dropdown_item_1line 表示适配器的下拉风格
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, autoStrings);

        auto.setAdapter(adapter);

        mul = (MultiAutoCompleteTextView) findViewById(R.id.mul);
        mul.setAdapter(adapter);
        mul.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());//完成对选项的拆分的功能，以逗号进行拆分

    }


}
