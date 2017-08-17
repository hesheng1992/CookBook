package com.example.gp62.cookbook.database

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by GP62 on 2017/8/17.
 */

class DBHelper : SQLiteOpenHelper {


    private val version = 1

    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory, version: Int) : super(context, name, factory, version) {}
    /**
     * CookBook 数据库名字， 1 版本号
     */
    constructor(context: Context) : super(context, "CookBook", null, 1) {

    }

    constructor(context: Context, name: String, factory: SQLiteDatabase.CursorFactory, version: Int, errorHandler: DatabaseErrorHandler) : super(context, name, factory, version, errorHandler) {}


    override fun onCreate(db: SQLiteDatabase) {
        //只有第一次创建数据才会执行
        //可以有多张表
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //更新数据库版本会执行这里

    }
}
