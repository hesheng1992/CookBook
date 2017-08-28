package com.example.gp62.cookbook.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import com.bm.library.Info
import com.bm.library.PhotoView
import com.example.gp62.cookbook.R
import com.example.gp62.cookbook.annotion.BindViewID
import com.example.gp62.cookbook.bitmap.blurBitmap
import com.example.gp62.cookbook.inteface.CallBackPhotoImpl
import com.example.gp62.cookbook.sp.setString

/**
 * Created by GP62 on 2017/8/13.
 */
open class BaseAvtivity : AppCompatActivity(), View.OnClickListener {

    private var refresh: SwipeRefreshLayout? = null

    var frame: FrameLayout? = null

    //显示全屏照片
    var photoView: PhotoView? = null

    var inmae: ImageView? = null
    internal var into = AlphaAnimation(0f, 1f)
    internal var outto = AlphaAnimation(1f, 0f)

    internal var mInfo: Info? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.photoview -> {
//                inmae?.startAnimation(outto)
                photoView?.animaTo(mInfo, object : Runnable {
                    override fun run() {
                        frame?.visibility = View.GONE
                    }
                })
            }
        }
    }

    /**
     * 转换图片为bitmap
     */
    fun bluBitemapGet(res: Int): Bitmap {
        val bmpimg = BitmapFactory.decodeResource(resources, res)
        //模糊图片
        var bitimg = blurBitmap(Bitmap.createBitmap(bmpimg), this)
        return bitimg
    }

    //回调显示图片
    var photoviewShowImpl = object : CallBackPhotoImpl {
        override fun showPhotoView(string: String, phView: PhotoView) {
            var p = phView
            //从imageview中获取设置的图片
            p.isDrawingCacheEnabled = true
            val createBitmap = Bitmap.createBitmap(p.getDrawingCache(true))
            p.isDrawingCacheEnabled = false
            getShowPhotoView(createBitmap, p)
        }
    }

    /**
     * 显示放大图片
     */
    fun getShowPhotoView(bitmap: Bitmap, p: PhotoView) {
        photoView?.setImageBitmap(bitmap)
        mInfo = p.info
        frame?.visibility = View.VISIBLE
        inmae?.startAnimation(into)
        photoView?.visibility = View.VISIBLE
        photoView?.animaFrom(mInfo)
    }

    /**
     * 初始化下刷新
     */
    fun setSwipLayput(swip: SwipeRefreshLayout) {
        this.refresh = swip
        refresh?.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light
                , android.R.color.holo_red_light, android.R.color.holo_orange_light)
        refresh?.setOnRefreshListener(onRefrsh)
    }

    /**
     * 设置刷新
     */
    val onRefrsh = object : SwipeRefreshLayout.OnRefreshListener {
        override fun onRefresh() {
            startRefresh()
        }
    }

    open fun startRefresh() {

    }


    internal var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
            //执行查询数据的方法
                MAINCALLBACKQURAY -> {
                    //存入share里面
                    setString("dataQuray", msg.obj as String)
                }
            }
        }
    }
    /**
     * 停止刷新
     */
    fun stopRefresh() {
        val refreshing = refresh?.isRefreshing
        if (refreshing as Boolean) {
            refresh?.isRefreshing = false
        }
    }

    /**
     * 提示
     */
    fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

}