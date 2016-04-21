package com.finalzhou.mbilesafe.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 版权： 凡路实验室安卓手机应用开发部（c）2015
 * 作者： 周健
 * 版本： 1.0
 * 创建日期：2015/10/3 23：04
 * 添加描述：
 *          数据库操作类,创建黑名单的数据库的数据表
 * 修订历史版本：
 */
public class BlackNumberOpenHelper extends SQLiteOpenHelper {
    public BlackNumberOpenHelper(Context context) {
        //第一个参数表示上下文
        //第二个参数表示数据库的名字
        //第三个参数表示游标工厂
        //第四个参数表示数据库版本号（必须大于0）
        super(context, "callsafe.db", null, 1);
    }


/**
 * 版权： 凡路实验室安卓手机应用开发部（c）2015
 * 作者： 周健
 * 创建日期：2015/10/3 23:19
 * 版本： 1.0
 * 添加描述：
 *      mode:表示拦截的模式
 *      number:表示黑名单的电话号码
 *  修订历史版本：
 */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE blacknumber(_id integer primary key autoincrement,name varchar(20),number varchar(20),mode varchar(2))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
