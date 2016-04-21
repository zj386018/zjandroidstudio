package com.finalzhou.myseekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private TextView textView1;
    private TextView textView2;
    private SeekBar seekBar1;
    private SeekBar seekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) this.findViewById(R.id.textview1);
        textView2 = (TextView) this.findViewById(R.id.textview2);
        seekBar1 = (SeekBar) this.findViewById(R.id.seekbar1);
        seekBar2 = (SeekBar) this.findViewById(R.id.seekbar2);

        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);


    }


    /**
     * 添加描述：
     * 滑动游标时会触发的事件
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (seekBar.getId() == R.id.seekbar1) {
                textView1.setText("seekBar1的进度为："+progress+"%");
                seekBar2.setSecondaryProgress(progress);

        }else{
            textView2.setText("seekBar2的进度为："+progress+"%");
        }

    }

    /**
     * 表示从哪里开始拖动
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        if (seekBar.getId() == R.id.seekbar1) {
            textView1.setText("seekBar1开始拖动");
        }else{
            textView2.setText("seekBar2开始拖动");
        }


    }


    /**
     * 表示从哪里结束拖动
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        if (seekBar.getId() == R.id.seekbar1) {
            textView1.setText("seekBar1结束拖动");
        }else{
            textView2.setText("seekBar2结束拖动");
        }

    }
}
