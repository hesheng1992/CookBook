package com.example.gp62.cookbook.view.dialog

import android.app.Dialog
import android.content.Context
import android.support.annotation.StyleRes
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.gp62.cookbook.R

/**
 * Created by GP62 on 2017/8/20.
 */
class LoadingDialog :Dialog{
    /**
     * 布局
     */
    var resID=0
    private var mContext :Context?=null
    var loadAnimation :Animation?=null
    private var image :ImageView?=null

    //加载透明背景对话框
    constructor(context: Context,resID: Int) :this(context, R.style.Pay_dialog,resID){
        this.resID=resID
        this.mContext=context
        init()
    }

    constructor(context: Context, @StyleRes themeResId :Int,resID :Int) :super(context,themeResId)


    fun init(){
        val view = LayoutInflater.from(mContext).inflate(R.layout.loading_dialog, null)
        image = view.findViewById(R.id.loading_image) as ImageView
        image?.setImageResource(resID)
        //设置动画
        loadAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading_rote)
        image?.animation=loadAnimation
        image?.startAnimation(loadAnimation)
        setCancelable(false)
        this.setContentView(view)
    }

    override fun show() {
        super.show()
        image?.startAnimation(loadAnimation)
    }

    override fun dismiss() {
        super.dismiss()
    }
}