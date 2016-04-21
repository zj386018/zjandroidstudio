package com.finalzhou.mbilesafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.finalzhou.mbilesafe.adapter.BlackNumberAdapter;
import com.finalzhou.mbilesafe.bean.BlackNumberInfo;
import com.finalzhou.mbilesafe.db.dao.BlackNumberDao;

import java.util.List;


public class BlackNumberActivity extends Activity {

    private BlackNumberDao dao;
    private List<BlackNumberInfo> blackNumberInfos;
    private Button bt_add_black_number;
    private ListView list_view;
    private BlackNumberAdapter adapter;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        设置当前界面没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_black_number);
        initview();
        initdata();
    }

    private void initview() {
//        添加黑名单
        bt_add_black_number = (Button) findViewById(R.id.bt_add_black_number);
        bt_add_black_number.setOnClickListener(new SetBt_addOnclickListener());

//        列表控件
        list_view = (ListView) findViewById(R.id.list_view);
    }

    /**
     * 添加黑名单按钮的点击事件的监控
     */
    public class SetBt_addOnclickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_add_black_number:
                    addBlackNumber();
                    break;
            }
        }
    }

    /**
     * 添加黑名单的对话框
     */
    private void addBlackNumber() {
//        初始化一个对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //绑定黑名单对话框
        View view = View.inflate(this, R.layout.dialog_add_black_number, null);
        //黑名单姓名
        final EditText et_name = (EditText) view.findViewById(R.id.et_name);
        //黑名单电话号码
        final EditText et_phone = (EditText) view.findViewById(R.id.et_phone);
        //电话拦截
        final CheckBox cb_phone = (CheckBox) view.findViewById(R.id.cb_phone);
        //短信拦截
        final CheckBox cb_sms = (CheckBox) view.findViewById(R.id.cb_sms);

        //确定按钮绑定
        Button bt_ok = (Button) view.findViewById(R.id.bt_ok);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的黑名单姓名
                String str_name = et_name.getText().toString().trim();

                //获取用户输入的黑名单电话号码
                String str_phone = et_phone.getText().toString().trim();
                //判断输入是否为空
                if (TextUtils.isEmpty(str_phone)) {
                    Toast.makeText(BlackNumberActivity.this, "电话号码输入不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                String mode = "";
                if (cb_phone.isChecked()&&cb_sms.isChecked()){
                    mode = "1";
                }else if(cb_phone.isChecked()){
                    mode = "2";
                }else if (cb_sms.isChecked()){
                    mode = "3";
                }else{
                    Toast.makeText(BlackNumberActivity.this, "必须选择一种拦截模式", Toast.LENGTH_SHORT).show();
                    return;
                }

                BlackNumberInfo blackNumberInfo = new BlackNumberInfo(mode,str_name,str_phone);
                dao.add(blackNumberInfo);
                blackNumberInfos.add(blackNumberInfo);
                adapter.notifyDataSetChanged();

                dialog.dismiss();
            }
        });

        //取消按钮绑定
        Button bt_cancel = (Button) view.findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        builder.setView(view);
        dialog = builder.show();
    }

    /**
     * 初始化数据，得到所有的黑名单，放在blackNumberInfos集合中
     */
    private void initdata() {
        dao = new BlackNumberDao(this);
        new Thread() {
            public void run() {
                //在安卓系统中，所有耗时操作必须放在子线程中
                //获取所有的黑名单电话号码
                blackNumberInfos = dao.findAll();
                //子线程是不能刷新UI的，只能调用runOnUiThread()方法来通过主线程来刷新
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {//在主线程中运行
                        adapter = new BlackNumberAdapter(BlackNumberActivity.this, blackNumberInfos);
                        list_view.setAdapter(adapter);

                    }
                });
            }
        }.start();


    }

}
