package com.finalzhou.mbilesafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finalzhou.mbilesafe.R;
import com.finalzhou.mbilesafe.bean.BlackNumberInfo;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 版权： 凡路实验室安卓手机应用开发部（c）2015
 * 作者： 周健
 * 版本： 1.0
 * 创建日期：2015/10/4 15：33
 * 添加描述：黑名单ListView适配器，用于向listview的每条item传递参数
 * 修订历史版本：
 */
public class BlackNumberAdapter extends BaseAdapter {

    private List<BlackNumberInfo> blackNumberInfos = null;
    private Context context;

    public BlackNumberAdapter(Context context,List<BlackNumberInfo> blackNumberInfos){
        super();
        this.blackNumberInfos = blackNumberInfos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return blackNumberInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return blackNumberInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //复用判断
        if (convertView == null){
            convertView = View.inflate(context, R.layout.item_black_number,null);
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
        TextView tv_mode = (TextView) convertView.findViewById(R.id.tv_mode);

        tv_name.setText(blackNumberInfos.get(position).getName());
        tv_phone.setText(blackNumberInfos.get(position).getNumber());

        String mode = blackNumberInfos.get(position).getMode();
        String str_mode = "";
        if(mode.equals("1")){
            str_mode = "全部拦截";
        }else if (mode.equals("2")){
            str_mode = "电话拦截";
        }else if (mode.equals("3")){
            str_mode = "短信拦截";
        }

        tv_mode.setText(str_mode);

        return convertView;
    }


}
