package com.example.gp62.cookbook.adpater

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by GP62 on 2017/8/15.
 */
/**
 * viewPager适配器类
 */
class ViewPagerAdapter(fm :FragmentManager) : FragmentPagerAdapter(fm) {

    private var lists: ArrayList<Fragment>?=null
    private  lateinit var listTitle :ArrayList<String>
    //二级构造函数
    constructor(fm:FragmentManager, list:ArrayList<Fragment>?, listTitle: ArrayList<String>) : this(fm){
        this.lists=list
        this.listTitle=listTitle
    }


    override fun getItem(position: Int): Fragment {
        //如果是空的话就会报npe异常
        return lists?.get(position)!!
    }

    override fun getCount(): Int {
        //如果不为0就返回大小，否则返回0
        return lists?.size ?:0
    }

    override fun getPageTitle(position: Int): CharSequence {
        return listTitle[position]
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
//        if (container!=null){
//            container.removeAllViews()
//        }else{
//            container?.addView(view)
//        }

        return super.instantiateItem(container, position)
    }
//
//    override fun isViewFromObject(arg0: View?, arg1: Any): Boolean {
//        // TODO Auto-generated method stub
//        return arg0 === arg1    //这行代码很重要，它用于判断你当前要显示的页面
//    }

}