package com.example.gp62.cookbook.sp

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bitmap.blurBitmap

/**
 * Created by GP62 on 2017/8/16.
 */
//全局的sharepre
var sharePrefensUtils: SharedPreferences?=null

fun initSharPrefence(context: Context) :SharedPreferences{
    var sharePrefensUtils=context.getSharedPreferences("初始化",Context.MODE_PRIVATE)
    return sharePrefensUtils
}

fun setBitmap(context :Context){

    val drawable = BitmapDrawable(bluBitemapGet(R.mipmap.shucai2,context))
    val edit = sharePrefensUtils?.edit()

}
/**
 * 转换图片为bitmap
 */
fun bluBitemapGet(res :Int,context: Context) : Bitmap {
    val bmpimg = BitmapFactory.decodeResource(context.resources, res)
    //模糊图片
    var bitimg= blurBitmap(Bitmap.createBitmap(bmpimg),context)
    return bitimg
}

fun setString(strname: String,string: String){
    val edit = sharePrefensUtils?.edit()
    edit?.putString(strname,string)
    edit?.commit()
}

/**
 * 得到string
 */
fun getStr(strname: String,strDefult :String) :String{
    val string = sharePrefensUtils?.getString(strname,strDefult)
    return string ?:strDefult
}