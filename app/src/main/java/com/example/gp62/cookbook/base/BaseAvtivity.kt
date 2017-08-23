package com.example.gp62.cookbook.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.bm.library.PhotoView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.bitmap.blurBitmap
import com.example.gp62.cookbook.inteface.CallBackPhotoImpl
import com.example.gp62.cookbook.sp.setString

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

    //回调显示图片
//    var photoviewShowImpl=object : CallBackPhotoImpl {
//        override fun showPhotoView(string: String, phView: PhotoView) {
//            var p=phView
//            //从imageview中获取设置的图片
//            p.isDrawingCacheEnabled=true
//            val createBitmap = Bitmap.createBitmap(p.getDrawingCache(true))
//            p.isDrawingCacheEnabled=false
//            getShowPhotoView(createBitmap)
//        }
//    }

    /**
     * 子类复写，执行相应的操作
     */
    fun getShowPhotoView(bitmap: Bitmap){

    }

    internal var handler: Handler=object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when(msg?.what){
                //执行查询数据的方法
                MAINCALLBACKQURAY ->{
                    //存入share里面
                    setString("dataQuray",msg.obj as String)
                }
            }
        }
    }


}