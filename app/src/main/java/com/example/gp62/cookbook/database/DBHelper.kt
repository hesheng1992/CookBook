package com.example.gp62.cookbook.database

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by GP62 on 2017/8/17.
 */
/**
 * 创建数据库
 */
class DBHelper : SQLiteOpenHelper {


    private val version = 1

    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory, version: Int) : super(context, name, factory, version) {}
    /**
     * CookBook 数据库名字， 1 版本号
     */
    constructor(context: Context) : super(context, "CookBookTest.db", null, 1) {

    }

    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory, version: Int, errorHandler: DatabaseErrorHandler) : super(context, name, factory, version, errorHandler) {}


    override fun onCreate(db: SQLiteDatabase) {
        //只有第一次创建数据才会执行
        //可以有多张表

        //创建一个存储搜索过的菜名,搜索次数
        db.execSQL("create table serchnum(name varchar(20),num integer)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //更新数据库版本会执行这里

    }
}
