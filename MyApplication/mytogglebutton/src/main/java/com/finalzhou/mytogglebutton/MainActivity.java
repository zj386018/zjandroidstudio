package com.finalzhou.mytogglebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.togglebutton);
        final LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.mylayout);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    linearLayout.setOrientation(LinearLayout.VERTICAL);//表示设置垂直布局
                }else{
                    linearLayout.setOrientation(linearLayout.HORIZONTAL);//表示设置水平布局
                }

            }
        });


    }


}
