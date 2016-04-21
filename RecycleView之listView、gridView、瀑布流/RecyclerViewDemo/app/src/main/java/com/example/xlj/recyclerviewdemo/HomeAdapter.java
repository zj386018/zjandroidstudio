package com.example.xlj.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by xlj on 2016/3/29.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
    private Context context;
    private List<String> mDatas;
    public HomeAdapter(Context context,List<String> mDatas){
        this.context=context;
        this.mDatas=mDatas;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
        //设置瀑布流item随机高度
        Random random=new Random();
        holder.tv.setHeight(random.nextInt(100)+300);

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyViewHolder(View view) {
            super(view);
            tv=(TextView)view.findViewById(R.id.id_num);
        }
    }

    /**
     * 根据position增加数据
     * @param position
     */
    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }
/*
 *根据position移除数据
 */
    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 设置item单击长按监听
     */
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
