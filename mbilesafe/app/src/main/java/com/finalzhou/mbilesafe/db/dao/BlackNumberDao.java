package com.finalzhou.mbilesafe.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.finalzhou.mbilesafe.bean.BlackNumberInfo;
import com.finalzhou.mbilesafe.db.BlackNumberOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 版权： 凡路实验室安卓手机应用开发部（c）2015
 * 作者： 周健
 * 联系电话：15388052293
 * 版本： 1.0
 * 创建日期：2015/10/3 23：30.
 * 添加描述：
 * <p/>
 * 修订历史版本：
 */
public class BlackNumberDao {

    private BlackNumberOpenHelper helper;

    public BlackNumberDao(Context context) {
        helper = new BlackNumberOpenHelper(context);
    }

    /**
     * 添加黑名单
     */
    public boolean add(BlackNumberInfo blackNumberInfo) {
        SQLiteDatabase db = helper.getWritableDatabase();// 取得数据库操作实例
// ***************方法一：
        //自定义sql操作语句来实现数据库的数据插入（将该方法boolean修饰词改为void）
        //db.execSQL("insert into blacknumber(name,number,mode) values(?,?,?)",
        // new Object[]{blackNumberInfo.getName(),blackNumberInfo.getNumber(), blackNumberInfo.getMode()});

// ***************方法二：
        //直接调用SQLiteDatabase封装好的方法来实现数据库的数据插入
        ContentValues values = new ContentValues();
        values.put("name", blackNumberInfo.getName());
        values.put("number", blackNumberInfo.getNumber());
        values.put("mode", blackNumberInfo.getMode());
//        					       空值字段（values值传入为null时或者为空集合时就用到 ）
//        					  表        ↓           传入要存储的数据
        long rowId = db.insert("blacknumber", null, values);
        db.close();
        if (rowId == -1) {
            return false;
        }
        return true;
    }

    /**
     * 根据电话号码进行删除
     *
     * @param number 将被移出黑名单的电话号码
     * @return
     */
    public boolean delete(String number) {
        SQLiteDatabase db = helper.getWritableDatabase();// 取得数据库操作实例
// ***************方法一：
        //自定义sql操作语句来实现数据库的数据删除（将该方法boolean修饰词改为void）
        //db.execSQL("delete from blacknumber where number=?", new Object[]{number});

// ***************方法二：
        //直接调用SQLiteDatabase封装好的方法来实现数据库的数据删除
        int rowNumber = db.delete("blacknumber", "number", new String[]{number});
        //如果rowNumber等于0，表n示该语句在数据库中执行后并没有影响到任何一条数据
        db.close();
        if (rowNumber == 0) {
            return false;
        }
        return true;
    }


    /**
     * 修改黑名单信息
     *
     * @param number
     * @param blackNumberInfo
     * @return
     */
    public boolean changeNumberInfo(String number, BlackNumberInfo blackNumberInfo) {
        SQLiteDatabase db = helper.getWritableDatabase();// 取得数据库操作实例
// ***************方法一：
//        db.execSQL("updata blacknumber set name=?,number=?,mode=? where number=?",
//                new Object[]{blackNumberInfo.getName(),
//                        blackNumberInfo.getNumber(), blackNumberInfo.getMode(), number});

// ***************方法二：
        ContentValues values = new ContentValues();
        values.put("name", blackNumberInfo.getName());
        values.put("number", blackNumberInfo.getNumber());
        values.put("mode", blackNumberInfo.getMode());
        int rowNumber = db.update("blacknumber", values, "number = ?", new String[]{number});
        db.close();
        if (rowNumber == 0) {
            return false;
        }
        return true;
    }

    /**
     * 查询所有黑名单的数据
     * @return
     */
    public List<BlackNumberInfo> findAll() {
        List<BlackNumberInfo> blackNumberInfos = new ArrayList<BlackNumberInfo>();
        SQLiteDatabase db = helper.getReadableDatabase();

// ***************方法一：
//        Cursor cursor = db.rawQuery("select * from blacknumber",null);
//        while(cursor.moveToNext()){//若能查询到数据，最少只有一条，则能移动到第一条
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String number = cursor.getString(cursor.getColumnIndex("number"));
//            String mode = cursor.getString(cursor.getColumnIndex("mode"));
//            BlackNumberInfo blackNumberInfo = new BlackNumberInfo(mode,name,number);
//            blackNumberInfos.add(blackNumberInfo);
//        }
//

// ***************方法二：
        Cursor cursor = db.query("blacknumber", new String[]{"name", "number", "mode"}, null, null, null, null, null);
        while (cursor.moveToNext()) {//若能查询到数据，最少只有一条，则能移动到第一条
            String name = cursor.getString(0);
            String number = cursor.getString(1);
            String mode = cursor.getString(2);
            BlackNumberInfo blackNumberInfo = new BlackNumberInfo(mode, name, number);
            blackNumberInfos.add(blackNumberInfo);
        }

        cursor.close();
        db.close();
        return blackNumberInfos;
    }


}
