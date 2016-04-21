package com.example.asus.mytreeview;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.mytreeview.adapter.SimpleTreeListViewAdapter;
import com.example.asus.mytreeview.bean.FileBean;
import com.example.asus.mytreeview.utils.Node;
import com.example.asus.mytreeview.utils.adapter.TreeListViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView mTree;
    private SimpleTreeListViewAdapter<FileBean> mAdapter;

    private List<FileBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTree = (ListView) findViewById(R.id.id_listview);

        initDatas();

        try {
            mAdapter = new SimpleTreeListViewAdapter<>(mTree,this,mDatas,1);//参数1为用户传入的数据，表示展开的层级数
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        initEvent();

    }

    private void initEvent() {
        mAdapter.setOnTreeNodeClickListener(new TreeListViewAdapter.OnTreeNodeClickListener() {
            @Override
            public void onClick(Node node, int position) {
                if (node.isLeaf()) {
                    Toast.makeText(MainActivity.this, node.getName(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        mTree.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {

                //
                final EditText et = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this).setTitle("Add Node").setView(et).setPositiveButton("sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.addExtraNode(position,et.getText().toString());


                    }
                }).setNegativeButton("Cancel", null).show();



                return true;
            }
        });


    }

    private void initDatas() {

        mDatas = new ArrayList<FileBean>();
        FileBean bean = new FileBean(1,0,"根目录1");
        mDatas.add(bean);
        bean = new FileBean(2,0,"根目录2");
        mDatas.add(bean);
        bean = new FileBean(3,0,"根目录3");
        mDatas.add(bean);
        bean = new FileBean(4,1,"根目录1-1");
        mDatas.add(bean);
        bean = new FileBean(5,1,"根目录1-2");
        mDatas.add(bean);
        bean = new FileBean(6,5,"根目录1-2-1");
        mDatas.add(bean);
        bean = new FileBean(7,3,"根目录3-1");
        mDatas.add(bean);
        bean = new FileBean(8,3,"根目录3-2");
        mDatas.add(bean);

    }


}
