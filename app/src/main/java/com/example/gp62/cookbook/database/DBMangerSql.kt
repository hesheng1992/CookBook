package com.example.gp62.cookbook.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created by GP62 on 2017/8/17.
 */
class DBMangerSql private constructor(context: Context) {
    /**
     * 初始化数据库
     */
    var dbHlper = DBHelper(context)
    /**
     * 获取数据库操作对象，执行增加，删除，更新
     */


    //创建单列模式
    companion object {
        var dbMangger: DBMangerSql? = null
        fun getInstanse(context: Context): DBMangerSql? {
            synchronized(DBMangerSql.javaClass) {
                if (dbMangger == null) {
                    dbMangger = DBMangerSql(context)
                }
            }
            return dbMangger
        }
    }


    /**
     * 增加数据  codeName 判断是更新还是插入
     * 如果是更新，那么数组第二个值是通过什么名字来定位哪一条
     */
    open  fun addAndUpateDataBase (tabname: String, params: Array<String?>, codename:String) {

        synchronized(DBMangerSql.javaClass){
            val dbDataBase = dbHlper.writableDatabase
            if (codename.equals("insert")){
                var con=ContentValues()
                con.put("name",params[0])
                con.put("num",1)
                dbDataBase.insert(tabname,null,con)
            }

            if (codename.equals("update")){
                var num=qureyNUm(params[1] as String,datbase =dbDataBase )
                if (num==0){
                    return
                }
                var con=ContentValues()
                con.put("num",num+1)
                var arr= arrayOf(params[1])
                //更新次数
                dbDataBase.update(tabname,con,"name=?",arr)
            }
            dbDataBase.close()
        }
    }

    /**
     * 查询数据库
     */
    open fun qureyData() :ArrayList<HashMap<String,Int>>{
        synchronized(DBMangerSql.javaClass){
            val dbDataBase = dbHlper.writableDatabase
            //返回name和对应的搜索次数
            var list=ArrayList<HashMap<String,Int>>()
            //查询前20条 降序排序
            var sql="select * from serchnum order by num desc "
            //返回cursor
            val rawQuery = dbDataBase.rawQuery(sql, null)
            while (rawQuery.moveToNext()){
                var map=HashMap<String,Int>()
                //获取名字
                val string = rawQuery.getString(rawQuery.getColumnIndex("name"))
                val num = rawQuery.getInt(rawQuery.getColumnIndex("num"))
                map.put(string,num);
                list.add(map)
            }
            rawQuery.close()
            dbDataBase.close()
            return list
        }
    }

    /**
     * 查询是否有该条数据
     */
    open fun qurey(name :String) :Boolean{
        val dbDataBase = dbHlper.writableDatabase
        var sql="select * from serchnum where name=?"
        val rawQuery = dbDataBase.rawQuery(sql, arrayOf(name))
        if (rawQuery.moveToFirst()){
            rawQuery.close()
            dbDataBase.close()
            return true
        }
        rawQuery.close()
        dbDataBase.close()
        return false
    }

    /**
     * 查出次数
     */
    open fun qureyNUm(name: String,datbase :SQLiteDatabase) :Int{
        synchronized(DBMangerSql.javaClass){
            var sql="select * from serchnum where name=?"
            val rawQuery = datbase.rawQuery(sql, arrayOf(name))
            while (rawQuery.moveToNext()){
                val num = rawQuery.getInt(rawQuery.getColumnIndex("num"))
                rawQuery.close()
                return num
            }
            rawQuery.close()
            return 0
        }
    }


}

