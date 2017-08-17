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

fun setBitmap(context :Context){
    var shar: SharedPreferences=context.getSharedPreferences("获取分类页面的主页面",Context.MODE_PRIVATE)
    val drawable = BitmapDrawable(bluBitemapGet(R.mipmap.shucai2,context))
    val edit = shar.edit()

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