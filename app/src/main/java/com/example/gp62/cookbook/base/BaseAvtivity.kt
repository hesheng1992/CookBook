package com.example.gp62.cookbook.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bitmap.blurBitmap

/**
 * Created by GP62 on 2017/8/13.
 */
open class BaseAvtivity :AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    /**
     * 转换图片为bitmap
     */
    fun bluBitemapGet(res :Int) :Bitmap{
        val bmpimg = BitmapFactory.decodeResource(resources, res)
        //模糊图片
        var bitimg= blurBitmap(Bitmap.createBitmap(bmpimg),this)
        return bitimg
    }


}