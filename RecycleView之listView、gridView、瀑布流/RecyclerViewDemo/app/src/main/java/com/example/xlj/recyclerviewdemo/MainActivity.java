package com.example.xlj.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        initData();
        mAdapter = new HomeAdapter(this, mDatas);
        //listview管理器
        //  mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //gridview管理器
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        //staggeredGrid管理器
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        //自定义listview分隔符
        // mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
        //   DividerItemDecoration.VERTICAL_LIST));
        //自定义gridview分隔符
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        // 设置item动画
        // mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //item单击长按时间响应
        mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    /**
     * 设置菜单增加移除数据
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                mAdapter.addData(1);
                break;
            case R.id.id_action_remove:
                mAdapter.removeData(1);
                break;
        }
        return true;
    }

}
