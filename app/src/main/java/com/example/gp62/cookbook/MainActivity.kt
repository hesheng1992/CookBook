package com.example.gp62.cookbook

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.Toast
import com.example.gp62.cookbook.adpater.ViewPagerAdapter
import com.example.gp62.cookbook.base.BaseAvtivity
import com.example.gp62.cookbook.fragment.MainFragment
import android.graphics.drawable.BitmapDrawable
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import com.bm.library.Info
import com.bm.library.PhotoView
import com.example.gp62.cookbook.annotion.BindViewID
import com.example.gp62.cookbook.base.getBindId
import com.example.gp62.cookbook.fragment.LeiXingFragment
import com.example.gp62.cookbook.inteface.CallBackPhotoImpl




class MainActivity : BaseAvtivity(),View.OnClickListener{

    @BindViewID(getId = R.id.toolbar2)
    private lateinit var toolar: Toolbar

    @BindViewID(getId = R.id.im_tool)
    private lateinit var image: ImageView

    @BindViewID(getId = R.id.viewPager)
    private lateinit var viewpage: ViewPager

    @BindViewID(getId = R.id.colltoolbar)
    private lateinit var collToolbar: CollapsingToolbarLayout

    @BindViewID(getId = R.id.tabLayout)
    private lateinit var tab: TabLayout

    private var listViewPager: ArrayList<Fragment>? = null

    private lateinit var fragmentManger: FragmentManager

    private var viewAdapter: ViewPagerAdapter? = null
    /**
     * tab的条目
     */
    private lateinit var listTitle: ArrayList<String>

    @BindViewID(getId = R.id.cood_main)
    private var liner_main: CoordinatorLayout? = null

    @BindViewID(getId=R.id.frame)
    private var frame : FrameLayout?=null

    //显示全屏照片
    @BindViewID(getId = R.id.photoview)
    private var photoView : PhotoView?=null

    @BindViewID(getId = R.id.frame_image)
    private var inmae :ImageView?=null

    internal var into = AlphaAnimation(0f, 1f)
    internal var outto = AlphaAnimation(1f, 0f)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)
        getBindId(this)//注解获取id
        intitView()
    }

    /**
     * 初始化
     */
    private fun intitView() {
        setSupportActionBar(toolar)
        //设置模糊图片
//        image.setImageBitmap(bluBitemapGet(R.mipmap.shucai))
        //设置模糊图片
//        Glide.with(this).load(R.mipmap.shucai).bitmapTransform(BlurTransformation(this,25)).into(image)
        //模糊图片
        val drawable = BitmapDrawable(bluBitemapGet(R.drawable.main_back))
        liner_main?.background = drawable
        addFragment()
        into.duration=300
        outto.duration=300

    }

    /**
     * 增加fragment
     */
    private fun addFragment() {
        listViewPager = java.util.ArrayList<Fragment>()
        listTitle = ArrayList()
        listTitle.add("搜索菜谱")
        listTitle.add("分类菜谱")
        fragmentManger = supportFragmentManager
        listViewPager?.add(MainFragment())
        listViewPager?.add(LeiXingFragment())
        viewAdapter = ViewPagerAdapter(fragmentManger, listViewPager, listTitle)
        viewpage.adapter = viewAdapter
        tab.tabMode = TabLayout.MODE_FIXED
        //关联viewpager
        tab.setupWithViewPager(viewpage, false)
        tab.addOnTabSelectedListener(on)
        viewpage.addOnPageChangeListener(viewChange)
        photoView?.setScaleType(ImageView.ScaleType.CENTER_INSIDE)
        photoView?.enable()
        photoView?.setOnClickListener(this)
    }

    internal var mInfo: Info?=null
    //回调显示图片
     var photoviewShowImpl=object :CallBackPhotoImpl{
        override fun showPhotoView(string: String, phView: PhotoView) {
            var p=phView
            //从imageview中获取设置的图片
            p.isDrawingCacheEnabled=true
            val createBitmap = Bitmap.createBitmap(p.getDrawingCache(true))
            p.isDrawingCacheEnabled=false

            photoView?.setImageBitmap(createBitmap)
            mInfo=p.info
            frame?.visibility=View.VISIBLE
            inmae?.startAnimation(into)
            photoView?.visibility=View.VISIBLE
            photoView?.animaFrom(mInfo)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.photoview ->{
                image.startAnimation(outto)
                photoView?.animaTo(mInfo,object :Runnable{
                    override fun run() {
                        frame?.visibility=View.GONE
                    }
                })
            }
        }
    }


    /**
     * viewPager页面改变时监听
     */
    private val viewChange=object :ViewPager.OnPageChangeListener{
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        @SuppressLint("ResourceType", "NewApi")
        override fun onPageSelected(position: Int) {
            //when表达式的使用
            when (position){
                //等于0
                0 ->{
                    val resources = getResources()
                    val drawableColl = resources.getDrawable(R.color.color_myproperty_dongjie)
                    collToolbar.contentScrim=drawableColl
                     tab.setSelectedTabIndicatorColor(getColor(R.color.color_myproperty_dongjie))
                    //设置模糊图片
//                    Glide.with(this@MainActivity).load(R.mipmap.shucai).bitmapTransform(BlurTransformation(this@MainActivity,25)).into(image)
                    //模糊图片
//                    val drawable = BitmapDrawable(bluBitemapGet(R.mipmap.beijing))
//                    liner_main?.background = drawable
                    }
                //等于1
                1 ->{
                    val resources = getResources()
                    val drawableColl = resources.getDrawable(R.color.color_myproperty_huodong)
                    collToolbar.contentScrim=drawableColl
                    tab.setSelectedTabIndicatorColor(getColor(R.color.color_myproperty_huodong))
                    //设置模糊图片
//                    Glide.with(this@MainActivity).load(R.mipmap.bar2).bitmapTransform(BlurTransformation(this@MainActivity,25)).into(image)
                    //模糊图片
//                    val drawable = BitmapDrawable(bluBitemapGet(R.mipmap.shucai2))
//                    liner_main?.background = drawable
                }
            }
        }
    }


    /**
     * 选择tab
     */
    private val on = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab) {

        }

        override fun onTabReselected(tab: TabLayout.Tab) {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 0) {
            Toast.makeText(this@MainActivity, "扫描成功回调", Toast.LENGTH_SHORT).show()
        }

    }
}
